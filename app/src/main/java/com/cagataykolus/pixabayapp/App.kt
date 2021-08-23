package com.cagataykolus.pixabayapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
