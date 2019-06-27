package com.dongnh.chappiebotnewfeed

import android.arch.lifecycle.MutableLiveData
import android.text.format.DateUtils
import android.view.View
import com.dongnh.chappiebotnewfeed.Adapter.AdapterGridView
import com.dongnh.chappiebotnewfeed.Base.BaseViewModel
import com.dongnh.chappiebotnewfeed.Model.Feed
import java.text.SimpleDateFormat
import java.util.*


class FeedViewModel : BaseViewModel(){
    // Adapter
    val adapterGridView: AdapterGridView = AdapterGridView()
    // Gridview
    val visibilityGrid: MutableLiveData<Int> = MutableLiveData()
    // Data
    private val stringTitle = MutableLiveData<String>()
    // Creater and date created
    private val nameAndDatePost = MutableLiveData<String>()

    // Bind data to view
    fun bind(feed: Feed){
        stringTitle.value = feed.title
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
        val time = sdf.parse(feed.published_date).getTime()
        val now = System.currentTimeMillis()

        val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.WEEK_IN_MILLIS)
        nameAndDatePost.value = feed.publisher?.name + " * " + ago
        if (feed.images.isNullOrEmpty()) {
            visibilityGrid.value = View.GONE
        } else {
            adapterGridView.updateImagesFeedList(feed.images)
        }
    }

    fun getStringTitle(): MutableLiveData<String> {
        return stringTitle
    }

    fun getNameAndDatePost(): MutableLiveData<String> {
        return nameAndDatePost
    }

}