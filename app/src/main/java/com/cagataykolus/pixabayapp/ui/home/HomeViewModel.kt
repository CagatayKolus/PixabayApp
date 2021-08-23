package com.cagataykolus.pixabayapp.ui.home

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataykolus.pixabayapp.data.repository.HitRepository
import com.cagataykolus.pixabayapp.model.Hit
import com.cagataykolus.pixabayapp.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val hitRepository: HitRepository) : ViewModel() {
    private val _hitsLiveData = MutableLiveData<State<List<Hit>>>()
    val hitsLiveData: LiveData<State<List<Hit>>> = _hitsLiveData

    fun getHits(query: String) {
        viewModelScope.launch {
            hitRepository.deleteAllHits()
                .onStart {}
                .map {}
                .collect {}
        }

        val timer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                viewModelScope.launch {
                    hitRepository.getAllHits(query = query)
                        .onStart { _hitsLiveData.value = State.loading() }
                        .map { resource -> State.fromResource(resource) }
                        .collect { state -> _hitsLiveData.value = state }
                }
            }
        }
        timer.start()
    }
}