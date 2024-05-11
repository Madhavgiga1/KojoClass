package com.example.reater.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reater.databinding.QuestionIndexCellLayoutBinding
import com.example.reater.models.Question

class QuestionIndexAdapter() : RecyclerView.Adapter<QuestionIndexAdapter.QuestionIndexViewHolder>() {

     var questionslist=emptyList<Question>()

     class QuestionIndexViewHolder(private val binding: QuestionIndexCellLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(questionNumber: Int) {
           // binding.questionNumber.text = questionNumber.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionIndexViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestionIndexCellLayoutBinding.inflate(inflater, parent, false)
        return QuestionIndexViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionIndexViewHolder, position: Int) {
        val questionNumber = position + 1 // Adjusting position to start from 1 instead of 0
        holder.bind(questionNumber)
    }

    override fun getItemCount(): Int {
        return questionslist.size
    }
}
