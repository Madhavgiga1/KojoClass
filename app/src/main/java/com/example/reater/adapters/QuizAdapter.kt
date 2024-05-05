package com.example.reater.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reater.databinding.FilesRowLayoutBinding
import com.example.reater.databinding.QuizRowLayoutBinding
import com.example.reater.models.Quiz
import com.example.reater.models.StudyMaterial
import com.example.reater.utils.GenericDiffUtil

class QuizAdapter: RecyclerView.Adapter<QuizAdapter.MyViewHolder>() {

    private var quizzes= emptyList<Quiz>()
    class MyViewHolder(private val binding: QuizRowLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(current_quiz: Quiz){
            binding.currentQuiz=current_quiz
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                var layoutInflater= LayoutInflater.from(parent.context)
                var binding= QuizRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current_quiz = quizzes[position]
        holder.bind(current_quiz)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    fun setData(newData: List<Quiz>){
        val announcementsDiffUtil =
            GenericDiffUtil(quizzes, newData)
        val diffUtilResult = DiffUtil.calculateDiff(announcementsDiffUtil)
        quizzes = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}