package com.cagataykolus.pixabayapp.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cagataykolus.pixabayapp.R
import com.cagataykolus.pixabayapp.databinding.AppbarBinding
import com.cagataykolus.pixabayapp.databinding.FragmentHomeBinding
import com.cagataykolus.pixabayapp.model.Hit
import com.cagataykolus.pixabayapp.model.State
import com.cagataykolus.pixabayapp.ui.dialog.NavigateBottomSheetAction
import com.cagataykolus.pixabayapp.ui.dialog.NavigateBottomSheetDialog
import com.cagataykolus.pixabayapp.ui.home.adapter.HomeAdapter
import com.cagataykolus.pixabayapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val bindingAppBar by viewBinding { AppbarBinding.bind(it) }
    private val viewModel by viewModels<HomeViewModel>()

    private val mAdapter = HomeAdapter(this::onItemClicked)
    private lateinit var searchView: SearchView

    override fun onStart() {
        super.onStart()

        observeHits()
        handleNetworkChanges()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTopAppBar()
        initMenu()
        initView()

        searchView.setQuery("fruits", false)
    }

    /**
     * Observe data.
     */
    private fun observeHits() {
        viewModel.hitsLiveData.observe(viewLifecycleOwner) { state ->
            hideKeyboard()
            when (state) {
                is State.Loading -> {
                    showLoading(true)
                }
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        mAdapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    showLoading(false)
                }
            }
        }
    }

    private fun getHits(query: String) = viewModel.getHits(query)

    /**
     * Initialize appbar.
     */
    private fun initTopAppBar() {
        bindingAppBar.topAppBar.inflateMenu(R.menu.menu)
        bindingAppBar.topAppBar.navigationIcon = null
        bindingAppBar.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> {
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Initialize menu which contains search bar.
     */
    private fun initMenu() {
        val searchItem = bindingAppBar.topAppBar.menu.findItem(R.id.action_search)
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }

        // Avoid search spam. Search will run after 1 second if text changes.
        searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let { txt ->
                    if (txt.isNotEmpty()) {
                        getHits(txt)
                    }
                }
            }
        )
    }

    /**
     * Initialize recyclerview with values.
     */
    private fun initView() {
        binding.run {
            recyclerviewHomeList.adapter = mAdapter
            swiperefreshlayoutHomeRefresh.setOnRefreshListener { getHits(searchView.query.toString()) }
        }

        viewModel.hitsLiveData.value?.let { currentState ->
            if (!currentState.isSuccessful()) {
                getHits(searchView.query.toString())
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swiperefreshlayoutHomeRefresh.isRefreshing = isLoading
    }

    /**
     * If click any item, navigate to detail fragment.
     */
    private fun onItemClicked(hit: Hit) {
        showBottomSheetDialog(hit, requireContext())
    }

    private fun showBottomSheetDialog(
        hit: Hit,
        context: Context
    ) {
        val dialog = NavigateBottomSheetDialog(context)
        dialog.onAction { action ->
            when (action) {
                NavigateBottomSheetAction.YesClicked -> {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailFragment,
                        bundleOf(
                            "DETAIL" to hit
                        )
                    )
                    dialog.dismiss()
                }
                NavigateBottomSheetAction.CancelClicked -> {
                    dialog.dismiss()
                }
            }
        }.show()
    }

    /**
     * Observes network changes.
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(requireContext()).observe(this) { isConnected ->
            if (!isConnected) {
                binding.textviewHomeNetworkStatus.text =
                    getString(R.string.internet_connectivity_fail)
                binding.linearlayoutHomeNetworkStatus.apply {
                    show()
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_fail
                        )
                    )
                }
            } else {
                if (viewModel.hitsLiveData.value is State.Error || mAdapter.itemCount == 0) {
                    getHits(searchView.query.toString())
                }
                binding.textviewHomeNetworkStatus.text =
                    getString(R.string.internet_connectivity_success)
                binding.linearlayoutHomeNetworkStatus.apply {
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_success
                        )
                    )

                    animate()
                        .alpha(1f)
                        .setStartDelay(1000L)
                        .setDuration(1000L)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        }
    }
}