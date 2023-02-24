package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.newsapp.adapter.ViewPagerAdapter
import com.example.newsapp.fragment.*
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val apiKey:String="42a9dd92bfc144f6ba69c1e5c06a1bcc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewPager=findViewById<ViewPager>(R.id.fragment_container)
        val tabLayout=findViewById<TabLayout>(R.id.include)

        val adapter= ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(),"Home")
        adapter.addFragment(SportsFragment(),"Sports")
        adapter.addFragment(HealthFragment(),"Health")
        adapter.addFragment(ScienceFragment(),"Science")
        adapter.addFragment(EntertainmentFragment(),"Entertainment")
        adapter.addFragment(TechnologyFragment(),"Technolony")

        viewPager.adapter=adapter
        tabLayout.setupWithViewPager(viewPager)



    }
}