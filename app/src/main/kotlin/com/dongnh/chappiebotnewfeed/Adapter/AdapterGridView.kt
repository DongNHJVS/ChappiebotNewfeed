package com.dongnh.chappiebotnewfeed.Adapter

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.dongnh.chappiebotnewfeed.Model.Image
import com.dongnh.chappiebotnewfeed.Model.ListFeed
import com.dongnh.chappiebotnewfeed.R
import com.dongnh.chappiebotnewfeed.databinding.CustomViewImageBinding
import android.support.design.widget.CoordinatorLayout.Behavior.setTag
import android.support.v7.widget.RecyclerView
import com.dongnh.chappiebotnewfeed.FeedViewModel
import com.dongnh.chappiebotnewfeed.ImageViewModel
import com.dongnh.chappiebotnewfeed.Model.Feed
import com.dongnh.chappiebotnewfeed.databinding.ItemViewBinding


class AdapterGridView : BaseAdapter() {

    private var dataList: List<Image> = emptyList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: CustomViewImageBinding
        if (convertView == null) {
            binding = CustomViewImageBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as CustomViewImageBinding
        }
        val viewModel = ImageViewModel()
        viewModel.bind(getItem(position))
        binding.viewModel = viewModel

        return binding.root
    }

    override fun getItem(position: Int): Image {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataList.size
    }

    // update view when data change
    fun updateImagesFeedList(listImages: List<Image>?){
        this.dataList = listImages?: dataList
        notifyDataSetChanged()
    }
}