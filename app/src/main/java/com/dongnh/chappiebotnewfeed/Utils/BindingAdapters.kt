package com.dongnh.chappiebotnewfeed.Utils

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dongnh.chappiebotnewfeed.Base.GlideApp
import com.dongnh.chappiebotnewfeed.CustomView.SquareImageView

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("adapterImage")
fun setAdapterImage(view: GridView, adapter: BaseAdapter) {
    view.adapter = adapter
}

@BindingAdapter("adapterDetail")
fun setAdapterDetail(view: ListView, adapter: BaseAdapter) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.VISIBLE})
    }
}

@BindingAdapter("mutableVisibilityGridView")
fun setMutableVisibilityGridView(view: GridView, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.GONE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("mutableTextImage")
fun setMutableTextImage(view: SquareImageView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer {
                value ->
            // Load when image null
            if (view.drawable == null) {
                GlideApp.with(view.context).load(text.value?: "")
                    .apply(
                        RequestOptions()
                            .dontAnimate()
                            .priority(Priority.HIGH)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .skipMemoryCache(false)
                    )
                    .into(view)
            }
        })
    }
}

@BindingAdapter("mutableImage")
fun setMutableImage(view: ImageView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer {
                value ->
            // Load when image null
            if (view.drawable == null) {
                GlideApp.with(view.context).load(text.value?: "")
                    .apply(
                        RequestOptions()
                            .dontAnimate()
                            .priority(Priority.HIGH)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .skipMemoryCache(false)
                    )
                    .into(view)
            }
        })
    }
}