package com.example.reater.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.reater.R
import com.example.reater.adapters.PagerAdapter
import com.example.reater.ui.fragments.SubjectDetail.FilesFragment
import com.example.reater.ui.fragments.SubjectDetail.PostsFragment
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList

class DetailsActivity : AppCompatActivity() {
    //private val args by navArgs<DetailsActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var viewpager=findViewById<ViewPager>(R.id.viewPager)
        var tablayout=findViewById<TabLayout>(R.id.tabLayout)
        var toolbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = "Subject Details"
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        //setSupportActionBar(toolbar)

        val fragments = ArrayList<Fragment>()
        fragments.add(PostsFragment())
        fragments.add(FilesFragment())


        val titles = ArrayList<String>()
        titles.add("Posts")
        titles.add("Files")

        val resultBundle = Bundle()
       // resultBundle.putString("recipeBundle", args.subjectLectureID)

        val pagerAdapter=PagerAdapter(resultBundle,fragments,titles,supportFragmentManager)

        viewpager.adapter = pagerAdapter
        tablayout.setupWithViewPager(viewpager)
    }
}