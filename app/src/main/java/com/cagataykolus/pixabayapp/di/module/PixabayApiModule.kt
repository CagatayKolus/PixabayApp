package com.cagataykolus.pixabayapp.di.module

import com.cagataykolus.pixabayapp.data.remote.api.PixabayService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class PixabayApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): PixabayService = Retrofit.Builder()
        .baseUrl(PixabayService.PIXABAY_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(PixabayService::class.java)
}
