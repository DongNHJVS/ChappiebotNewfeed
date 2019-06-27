package com.dongnh.chappiebotnewfeed

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.dongnh.chappiebotnewfeed.Base.BaseViewModel
import com.dongnh.chappiebotnewfeed.Model.Section

class SectionViewModel : BaseViewModel() {
    // Gallery visibility
    val visibilityGallery: MutableLiveData<Int> = MutableLiveData()
    // Video visibility
    val visibilityVideo: MutableLiveData<Int> = MutableLiveData()
    // Text visibility
    val visibilityText: MutableLiveData<Int> = MutableLiveData()
    // Image path
    private val imagePath: MutableLiveData<String> = MutableLiveData()
    // Image video
    private val imageVideoPath: MutableLiveData<String> = MutableLiveData()
    // Duration
    private val duration: MutableLiveData<String> = MutableLiveData()
    // Data
    private val stringText = MutableLiveData<String>()

    // Bind data to view
    fun bind(section: Section) {
        when (section.section_type) {
            1 -> {
                visibilityText.value = View.VISIBLE
                visibilityVideo.value = View.GONE
                visibilityGallery.value = View.GONE
                stringText.value = section.content?.text
            }
            2 -> {
                visibilityText.value = View.GONE
                visibilityVideo.value = View.VISIBLE
                visibilityGallery.value = View.GONE
                duration.value = calcTimeOfVideo(section)
                imageVideoPath.value = section.content?.preview_image?.href
            }
            3 -> {
                visibilityText.value = View.GONE
                visibilityVideo.value = View.GONE
                visibilityGallery.value = View.VISIBLE
                imagePath.value = section.content?.href
            }
            else -> {
                // Nothing
            }
        }
    }

    fun getStringText(): MutableLiveData<String> {
        return stringText
    }

    fun getImagePath(): MutableLiveData<String> {
        return imagePath
    }

    fun getImageVideoPath(): MutableLiveData<String> {
        return imageVideoPath
    }

    fun getDuration(): MutableLiveData<String> {
        return duration
    }

    // Calc time for view duration video
    fun calcTimeOfVideo(section: Section) : String {
        var minutes = (section.content?.duration?: 0) / 60
        val seconds = (section.content?.duration?: 0) - minutes * 60

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
}