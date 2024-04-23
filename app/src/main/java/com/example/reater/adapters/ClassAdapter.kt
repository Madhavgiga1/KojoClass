package com.example.reater.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reater.databinding.ClassesRowLayoutBinding


//what is this we used after RecyclerView.Adapter<>

//in MyViewholder is this primary construcor
class ClassAdapter: RecyclerView.Adapter<ClassAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ClassesRowLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}