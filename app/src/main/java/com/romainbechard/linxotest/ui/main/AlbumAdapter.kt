package com.romainbechard.linxotest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.romainbechard.linxotest.data.model.AlbumDetail
import com.romainbechard.linxotest.databinding.ItemAlbumBinding

class AlbumAdapter(private val viewModel: MainViewModel) :
    ListAdapter<AlbumDetail, AlbumAdapter.ViewHolder>(AlbumDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MainViewModel, item: AlbumDetail) {

            binding.viewModel = viewModel
            binding.album = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAlbumBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class AlbumDiffCallback : DiffUtil.ItemCallback<AlbumDetail>() {
    override fun areItemsTheSame(oldItem: AlbumDetail, newItem: AlbumDetail): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: AlbumDetail, newItem: AlbumDetail): Boolean {
        return oldItem == newItem
    }
}