package com.dongnh.chappiebotnewfeed.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.dongnh.chappiebotnewfeed.Model.Section
import com.dongnh.chappiebotnewfeed.SectionViewModel
import com.dongnh.chappiebotnewfeed.databinding.ItemDetailBinding

class AdapterDetail : BaseAdapter() {

    private var dataList: List<Section> = emptyList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemDetailBinding
        if (convertView == null) {
            binding = ItemDetailBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ItemDetailBinding
        }
        val viewModel = SectionViewModel()
        viewModel.bind(getItem(position))
        binding.viewModel = viewModel

        return binding.root
    }

    override fun getItem(position: Int): Section {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataList.size
    }

    // update view when data change
    fun updateSectionList(listSection: List<Section>?){
        this.dataList = listSection?: dataList
        notifyDataSetChanged()
    }
}