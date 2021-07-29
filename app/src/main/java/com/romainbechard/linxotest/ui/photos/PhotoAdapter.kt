package com.romainbechard.linxotest.ui.photos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.romainbechard.linxotest.R
import com.romainbechard.linxotest.data.model.Photo
import com.squareup.picasso.Picasso
import timber.log.Timber


class PhotoAdapter(
    private val ctx: Context,
    private val list: List<Photo>
) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var myView = view
        if (myView == null) {
            myView = LayoutInflater.from(ctx).inflate(R.layout.item_photo, parent, false)
            Timber.d("view is null, inflating")
        }
        val image = myView!!.findViewById(R.id.ivPhoto) as ImageView


        Timber.d("Picasso get")
        Picasso.get()
            .load(list[position].url)
            .into(image)

        return myView
    }

}