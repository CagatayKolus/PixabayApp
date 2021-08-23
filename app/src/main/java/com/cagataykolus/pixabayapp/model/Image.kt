package com.cagataykolus.pixabayapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Çağatay Kölüş on 21.08.2021.
 * cagataykolus@gmail.com
 */
@Parcelize
data class Image(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
):Parcelable {
}
