package com.cagataykolus.pixabayapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.cagataykolus.pixabayapp.R
import com.cagataykolus.pixabayapp.databinding.FragmentDetailBinding
import com.cagataykolus.pixabayapp.model.Hit
import com.cagataykolus.pixabayapp.util.viewBinding

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding { FragmentDetailBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = arguments?.getParcelable<Hit>("DETAIL")
        content?.let {
            showDetails(it)
        }
    }

    private fun showDetails(hit: Hit) {
        binding.textviewDetailUsername.text = hit.user
        binding.textviewDetailTags.text = hit.tags
        binding.imageviewDetailPhoto.load(hit.largeImageURL) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_broken_image)
        }

        if (hit.userImageURL.isEmpty()) {
            binding.imageviewDetailAvatar.load(R.drawable.ic_avatar)
        } else {
            binding.imageviewDetailAvatar.load(hit.userImageURL) {
                crossfade(true)
                placeholder(R.drawable.ic_avatar)
                transformations(CircleCropTransformation())
            }
        }
        binding.textviewDetailLike.text = hit.likes.toString()
        binding.textviewDetailComment.text = hit.comments.toString()
        binding.textviewDetailDownload.text = hit.downloads.toString()
    }
}