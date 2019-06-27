package com.dongnh.chappiebotnewfeed.Adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dongnh.chappiebotnewfeed.FeedViewModel
import com.dongnh.chappiebotnewfeed.Model.Feed
import com.dongnh.chappiebotnewfeed.Model.ListFeed
import com.dongnh.chappiebotnewfeed.R
import com.dongnh.chappiebotnewfeed.databinding.ItemViewBinding
import kotlinx.android.synthetic.main.item_view.view.*

class AdapterListView : RecyclerView.Adapter<AdapterListView.ViewHolder>() {
    private lateinit var dataList: List<Feed>
    lateinit var listener: OnItemClickListener

    // Create view of Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListView.ViewHolder {
        setOnItemClickListener(listener)
        val binding: ItemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_view, parent, false)
        return ViewHolder(binding)
    }

    // Bind data
    override fun onBindViewHolder(holder: AdapterListView.ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.view_gallery_main.setOnClickListener({ listener.onClick(it, dataList[position]) })
    }

    // Get item count
    override fun getItemCount(): Int {
        return if(::dataList.isInitialized) dataList.size else 0
    }

    // update view when data change
    fun updateNewsFeedList(listFeed: ListFeed){
        this.dataList = listFeed.items
        notifyDataSetChanged()
    }

    // interface Click on item
    interface OnItemClickListener {
        fun onClick(view: View, data: Feed)
    }

    // Set listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // Class bind data
    class ViewHolder(private val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = FeedViewModel()

        fun bind(feed: Feed){
            viewModel.bind(feed)
            binding.viewModel = viewModel
        }
    }
}