package com.cagataykolus.pixabayapp.ui.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.cagataykolus.pixabayapp.R
import com.cagataykolus.pixabayapp.databinding.ItemPhotoBinding
import com.cagataykolus.pixabayapp.model.Hit

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
class HomeViewHolder(private val binding: ItemPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(hit: Hit, onItemClicked: (Hit) -> Unit) {
        binding.textviewHomeUsername.text = hit.user
        binding.textviewHomeTags.text = hit.tags
        // hit.previewURL -> low resolution images with a maximum width or height of 150 px
        // Used largeImageURL instead of previewURL due to low resolution.
        binding.imageviewHomePhoto.load(hit.largeImageURL) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_broken_image)
        }

        if (hit.userImageURL.isEmpty()) {
            binding.imageviewHomeAvatar.load(R.drawable.ic_avatar)
        } else {
            binding.imageviewHomeAvatar.load(hit.userImageURL) {
                crossfade(true)
                placeholder(R.drawable.ic_avatar)
                transformations(CircleCropTransformation())
            }
        }

        binding.root.setOnClickListener {
            onItemClicked(hit)
        }
    }
}