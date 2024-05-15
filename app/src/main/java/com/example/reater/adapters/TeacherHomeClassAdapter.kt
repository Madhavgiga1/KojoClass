package com.example.reater.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reater.databinding.ClassesRowLayoutBinding
import com.example.reater.databinding.TeacherClassrowlayoutBinding
import com.example.reater.models.Coursework
import com.example.reater.utils.GenericDiffUtil

class TeacherHomeClassAdapter: RecyclerView.Adapter<TeacherHomeClassAdapter.MyViewHolder>() {
    var classlist = emptyList<Coursework>()

    class MyViewHolder(private val binding: TeacherClassrowlayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Coursework) {
            binding.classdetails=course

        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                var layoutInflater = LayoutInflater.from(parent.context)
                var binding = TeacherClassrowlayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentclass = classlist[position]
        holder.bind(currentclass)
    }

    override fun getItemCount(): Int {
        return classlist.size
    }

    fun setData(newData: List<Coursework>) {
        val classDiffUtil =
            GenericDiffUtil(classlist, newData)
        val diffUtilResult = DiffUtil.calculateDiff(classDiffUtil)
        classlist = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}