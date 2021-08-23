package com.cagataykolus.pixabayapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cagataykolus.pixabayapp.databinding.ItemPhotoBinding
import com.cagataykolus.pixabayapp.model.Hit
import com.cagataykolus.pixabayapp.ui.home.viewholder.HomeViewHolder

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
class HomeAdapter (
    private val onItemClicked: (Hit) -> Unit
) : ListAdapter<Hit, HomeViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Hit>() {
            override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean =
                oldItem == newItem
        }
    }
}
