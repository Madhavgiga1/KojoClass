package com.example.reater.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

class RowBinding {
    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
            }
        }
    }
}