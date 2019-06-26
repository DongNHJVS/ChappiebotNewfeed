package com.dongnh.chappiebotnewfeed

import android.arch.lifecycle.MutableLiveData
import com.dongnh.chappiebotnewfeed.Base.BaseViewModel
import com.dongnh.chappiebotnewfeed.Model.Feed
import com.dongnh.chappiebotnewfeed.Model.Image

class ImageViewModel : BaseViewModel(){

    // Data
    private val imagePath = MutableLiveData<String>()

    // Bind data to view
    fun bind(image: Image){
        imagePath.value = image.href?: ""
    }

    fun getImagePath(): MutableLiveData<String> {
        return imagePath
    }
}