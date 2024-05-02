package com.example.reater.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reater.databinding.ClassesRowLayoutBinding
import com.example.reater.models.Coursework
import com.example.reater.utils.GenericDiffUtil


//what is this we used after RecyclerView.Adapter<>

//in MyViewholder is this primary construcor
class ClassAdapter: RecyclerView.Adapter<ClassAdapter.MyViewHolder>() {
    var classlist=emptyList<Coursework>()
    class MyViewHolder(private val binding: ClassesRowLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(course: Coursework){
            binding.individualCourse=course

        }
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                var layoutInflater=LayoutInflater.from(parent.context)
                var binding=ClassesRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentcourse=classlist[position]
        holder.bind(currentcourse)
    }

    override fun getItemCount(): Int {
        return classlist.size
    }

    fun setData(newData: List<Coursework>){
        val classDiffUtil =
            GenericDiffUtil(classlist, newData)
        val diffUtilResult = DiffUtil.calculateDiff(classDiffUtil)
        classlist = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}