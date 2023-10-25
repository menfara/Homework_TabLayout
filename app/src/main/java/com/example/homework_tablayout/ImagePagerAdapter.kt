package com.example.homework_tablayout

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ImagePagerAdapter(
    private val context: Context,
    private val imageResources: List<Int>
) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pager_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val drawable = ContextCompat.getDrawable(context, imageResources[position])
        holder.imageView.adjustScaleType(drawable)
        holder.imageView.setImageDrawable(drawable)

    }

    private fun ImageView.adjustScaleType(drawable: Drawable?) {
        drawable?.let {
            if (it.intrinsicHeight < it.intrinsicWidth) {
                this.scaleType = ImageView.ScaleType.FIT_CENTER
            } else {
                this.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }
    }

    override fun getItemCount(): Int = imageResources.size

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}
