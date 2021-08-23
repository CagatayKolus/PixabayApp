package com.cagataykolus.pixabayapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cagataykolus.pixabayapp.model.Hit.Companion.TABLE_HIT
import kotlinx.android.parcel.Parcelize

/**
 * Created by Çağatay Kölüş on 21.08.2021.
 * cagataykolus@gmail.com
 */
@Parcelize
@Entity(tableName = TABLE_HIT)
data class Hit(
    @PrimaryKey
    val id: Int,
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
): Parcelable {
    companion object {
        const val TABLE_HIT = "table_hit"
    }
}