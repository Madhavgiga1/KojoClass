package com.example.reater.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reater.databinding.FilesRowLayoutBinding
import com.example.reater.models.StudyMaterial
import com.example.reater.utils.GenericDiffUtil

class FilesAdapter: RecyclerView.Adapter<FilesAdapter.MyViewHolder>() {

    private var studyMaterials= emptyList<StudyMaterial>()
    class MyViewHolder(private val binding: FilesRowLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(individualStudyMaterial: StudyMaterial){
            binding.individualStudymaterial=individualStudyMaterial
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                var layoutInflater= LayoutInflater.from(parent.context)
                var binding= FilesRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current_material = studyMaterials[position]
        holder.bind(current_material)
    }

    override fun getItemCount(): Int {
        return studyMaterials.size
    }

    fun setData(newData: List<StudyMaterial>){
        val announcementsDiffUtil =
            GenericDiffUtil(studyMaterials, newData)
        val diffUtilResult = DiffUtil.calculateDiff(announcementsDiffUtil)
        studyMaterials = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}