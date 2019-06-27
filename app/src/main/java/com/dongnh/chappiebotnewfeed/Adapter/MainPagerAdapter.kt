package com.dongnh.chappiebotnewfeed.Adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dongnh.chappiebotnewfeed.Fragment.BlankFragment
import com.dongnh.chappiebotnewfeed.Fragment.MainFragment
import com.dongnh.chappiebotnewfeed.R

class MainPagerAdapter(fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {

    var contextLocal : Context = context
    // Add new fragment in here

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> BlankFragment.newInstance()
        1 -> MainFragment.newInstance()
        2 -> BlankFragment.newInstance()
        3 -> BlankFragment.newInstance()
        4 -> BlankFragment.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> contextLocal.getString(R.string.string_follow);
        1 -> contextLocal.getString(R.string.string_foryou);
        2 -> contextLocal.getString(R.string.string_soccer);
        3 -> contextLocal.getString(R.string.string_technology);
        4 -> contextLocal.getString(R.string.string_life);
        else -> ""
    }

    override fun getCount(): Int = 5
}