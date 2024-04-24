package com.example.reater.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.reater.R
import com.example.reater.ui.fragments.SubjectDetail.FilesFragment
import com.example.reater.ui.fragments.SubjectDetail.PostsFragment
import java.util.ArrayList

class DetailsActivity : AppCompatActivity() {
    private val args by navArgs<DetailsActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val fragments = ArrayList<Fragment>()
        fragments.add(PostsFragment())
        fragments.add(FilesFragment())


        val titles = ArrayList<String>()
        titles.add("Posts")
        titles.add("Files")

        val resultBundle = Bundle()
        resultBundle.putString("recipeBundle", args.subjectLectureID)

    }
}