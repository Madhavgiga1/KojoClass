package com.example.reater.bindingadapters

import android.content.Intent
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.example.reater.models.Assignment
import com.example.reater.models.Coursework
import com.example.reater.models.Quiz
import com.example.reater.ui.activities.MainActivity
import com.example.reater.ui.activities.OngoingQuizActivity
import com.example.reater.ui.fragments.AssignmentFragmentDirections
import com.example.reater.ui.fragments.HomeFragmentDirections
import com.example.reater.ui.fragments.QuizFragmentDirections

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
        fun onCourseworkClickListener(recipeRowLayout: ConstraintLayout, coursework:Coursework){
            recipeRowLayout.setOnClickListener{
                try {
                    val action=HomeFragmentDirections.actionHomeFragmentToDetailsActivity(coursework)
                    recipeRowLayout.findNavController().navigate(action)
                } catch (e:Exception){
                    Log.d("onRecipeClickListener",e.toString())
                }
            }
        }

       /* @BindingAdapter("onQuizClickListener")
        @JvmStatic
        fun onQuizClickClickListener(quizRowLayout: ConstraintLayout, quiz: Quiz){
            quizRowLayout.setOnClickListener{
                try {
                    //val intent = Intent(quizRowLayout.context, OngoingQuizActivity::class.java)
                    // Add any extra data you need to pass to the activity here
                    //intent.putExtra("quiz", quiz)
                    //quizRowLayout.context.startActivity(intent)
                    //var intent= Intent(quizRowLayout.context, MainActivity::class.java)
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(intent)
                   val action=QuizFragmentDirections.actionQuizFragmentToOngoingQuizFragment(quiz)
                    quizRowLayout.findNavController().navigate(action)

                } catch (e:Exception){
                    Log.d("onRecipeClickListener",e.toString())
                }
            }
        }*/
        @BindingAdapter("onQuizClickActivityListener")
        @JvmStatic
        fun onQuizActivityClickListener(quizRowLayout: ConstraintLayout, quiz: Quiz){
            quizRowLayout.setOnClickListener{
                try {
                    //val intent = Intent(quizRowLayout.context, OngoingQuizActivity::class.java)
                    // Add any extra data you need to pass to the activity here
                    //intent.putExtra("quiz", quiz)
                    //quizRowLayout.context.startActivity(intent)
                    //var intent= Intent(quizRowLayout.context, MainActivity::class.java)
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(intent)
                    val action=QuizFragmentDirections.actionQuizFragmentToOngoingQuizActivity(quiz)
                    quizRowLayout.findNavController().navigate(action)

                } catch (e:Exception){
                    Toast.makeText(quizRowLayout.context,e.toString(),Toast.LENGTH_SHORT).show()
                    Log.d("ayooo",e.toString())
                    //getting this error cause you only made the quiz class parcelable
                }
            }
        }

        @BindingAdapter("onAssignmentClickListener")
        @JvmStatic
        fun onAssignmentClickListener(assignmentRowLayout: ConstraintLayout, assignment: Assignment){
            assignmentRowLayout.setOnClickListener{
                try {
                    val action=AssignmentFragmentDirections.actionAssignmentFragmentToAssignmentDetailsFragment(assignment)
                    assignmentRowLayout.findNavController().navigate(action)
                } catch (e:Exception){
                    Log.d("onRecipeClickListener",e.toString())
                }
            }
        }
    }
}