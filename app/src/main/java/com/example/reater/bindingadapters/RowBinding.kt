package com.example.reater.bindingadapters

import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.example.reater.models.Coursework
import com.example.reater.ui.fragments.HomeFragmentDirections

class RowBinding {
    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
            }
        }

        @BindingAdapter("onCourseworkClickListener")
        @JvmStatic
        fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, coursework:Coursework){
            recipeRowLayout.setOnClickListener{
                try {
                    val action=HomeFragmentDirections.actionHomeFragmentToDetailsActivity(coursework)
                    recipeRowLayout.findNavController().navigate(action)
                } catch (e:Exception){
                    Log.d("onRecipeClickListener",e.toString())
                }
            }
        }
    }
}