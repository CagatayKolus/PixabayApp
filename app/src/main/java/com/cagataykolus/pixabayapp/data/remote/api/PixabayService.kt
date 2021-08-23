package com.cagataykolus.pixabayapp.data.remote.api

import com.cagataykolus.pixabayapp.BuildConfig
import com.cagataykolus.pixabayapp.data.remote.api.PixabayService.Companion.PIXABAY_API_URL
import com.cagataykolus.pixabayapp.model.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Çağatay Kölüş on 21.08.2021.
 * cagataykolus@gmail.com
 */
/**
 * Service to fetch data using endpoint [PIXABAY_API_URL].
 */
interface PixabayService {
    @GET("api")
    suspend fun getImage(@Query("key") key: String = BuildConfig.PIXABAY_API_KEY,
                          @Query("q") query: String = "",
                          @Query("image_type") imageType: String = "photo"): Response<Image>

    companion object {
        const val PIXABAY_API_URL = "https://pixabay.com/"
    }
}