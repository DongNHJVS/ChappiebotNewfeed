package com.dongnh.chappiebotnewfeed.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import com.dongnh.chappiebotnewfeed.Adapter.MainPagerAdapter
import com.dongnh.chappiebotnewfeed.Fragment.MainFragment
import com.dongnh.chappiebotnewfeed.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewMain()
    }

    /**
     * Set up adapter for view pager
     *
     */
    private fun initViewMain() {
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val adapter = MainPagerAdapter(supportFragmentManager, this@MainActivity)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
