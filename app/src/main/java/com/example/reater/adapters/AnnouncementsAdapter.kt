package com.example.reater.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reater.databinding.AnnouncementRowLayoutBinding
import com.example.reater.models.Announcement
import com.example.reater.utils.GenericDiffUtil

class AnnouncementsAdapter: RecyclerView.Adapter<AnnouncementsAdapter.MyViewHolder>() {
    private var announcements= emptyList<Announcement>()
    class MyViewHolder(private val binding:AnnouncementRowLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(individualAnnouncement: Announcement){
            binding.individualAnnouncement=individualAnnouncement
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                var layoutInflater= LayoutInflater.from(parent.context)
                var binding=AnnouncementRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current_announcement = announcements[position]
        holder.bind(current_announcement)
    }

    override fun getItemCount(): Int {
        return announcements.size
    }

    fun setData(newData: List<Announcement>){
        val announcementsDiffUtil =
            GenericDiffUtil(announcements, newData)
        val diffUtilResult = DiffUtil.calculateDiff(announcementsDiffUtil)
        announcements = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}