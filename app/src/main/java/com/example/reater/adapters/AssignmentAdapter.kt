package com.example.reater.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reater.databinding.AssigmentRowLayoutBinding
import com.example.reater.databinding.FilesRowLayoutBinding
import com.example.reater.models.Assignment
import com.example.reater.models.StudyMaterial
import com.example.reater.utils.GenericDiffUtil

class AssignmentAdapter: RecyclerView.Adapter<AssignmentAdapter.MyViewHolder>() {

    private var assignments= emptyList<Assignment>()
    class MyViewHolder(private val binding: AssigmentRowLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(current_assignment: Assignment){
            binding.currentAssignment=current_assignment
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                var layoutInflater= LayoutInflater.from(parent.context)
                var binding= AssigmentRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current_assignment = assignments[position]
        holder.bind(current_assignment)
    }

    override fun getItemCount(): Int {
        return assignments.size
    }

    fun setData(newData: ArrayList<Assignment>){
        val announcementsDiffUtil =
            GenericDiffUtil(assignments, newData)
        val diffUtilResult = DiffUtil.calculateDiff(announcementsDiffUtil)
        assignments = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}