package com.romainbechard.linxotest.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.romainbechard.linxotest.data.model.AlbumDetail
import timber.log.Timber

@BindingAdapter("android:items")
fun setItems(listView: RecyclerView, items: List<AlbumDetail>?) {
    items?.let {
        Timber.d("setItems $items")
        (listView.adapter as AlbumAdapter).submitList(items)
    }
}