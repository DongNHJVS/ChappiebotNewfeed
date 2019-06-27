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
    // Gridview visibility
    val visibilityGrid: MutableLiveData<Int> = MutableLiveData()
    // Gallery visibility
    val visibilityGallery: MutableLiveData<Int> = MutableLiveData()
    // Video visibility
    val visibilityVideo: MutableLiveData<Int> = MutableLiveData()
    // Image path
    private val imagePath: MutableLiveData<String> = MutableLiveData()
    // Duration
    private val duration: MutableLiveData<String> = MutableLiveData()
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

        // No implement content_type is : overview, story, article, long_form
        if (feed.content_type.equals("video")) {
            // Show item video
            visibilityGallery.value = View.GONE
            visibilityVideo.value = View.VISIBLE
            imagePath.value = feed.content?.preview_image?.href

            duration.value = calcTimeOfVideo(feed)

        } else {
            // Show other item like gallery
            visibilityGallery.value = View.VISIBLE
            visibilityVideo.value = View.GONE
            if (feed.images.isNullOrEmpty()) {
                visibilityGrid.value = View.GONE
            } else {
                adapterGridView.updateImagesFeedList(feed.images)
            }
        }

    }

    // Calc time for view duration video
    fun calcTimeOfVideo(feed: Feed) : String {
        var minutes = (feed.content?.duration?: 0) / 60
        val seconds = (feed.content?.duration?: 0) - minutes * 60

        val secondString: String
        val minutesString: String
        var hourString: String
        // Calculation time to view
        if (seconds < 10) {
            secondString = "0$seconds"
        } else {
            secondString = "" + seconds
        }

        val hour = minutes / 60
        if (minutes > 60) {
            minutes = minutes - hour * 60
            hourString = " $hour:"
        } else if (minutes == 60) {
            minutes = 0
            hourString = " 01:"
        } else {
            hourString = " "
        }

        if (hour > 0) {
            hourString = " $hour:"
        }

        if (minutes < 10 && minutes > 0) {
            minutesString = "0$minutes:"
        } else if (minutes == 0) {
            minutesString = ""
        } else {
            minutesString = "$minutes:"
        }
        return hourString + minutesString + secondString
    }

    fun getStringTitle(): MutableLiveData<String> {
        return stringTitle
    }

    fun getNameAndDatePost(): MutableLiveData<String> {
        return nameAndDatePost
    }

    fun getImagePath(): MutableLiveData<String> {
        return imagePath
    }

    fun getDuration(): MutableLiveData<String> {
        return duration
    }

}