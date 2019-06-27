package com.dongnh.chappiebotnewfeed.Fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dongnh.chappiebotnewfeed.MainViewModel

import com.dongnh.chappiebotnewfeed.R
import com.dongnh.chappiebotnewfeed.databinding.MainFragmentBinding
import android.databinding.DataBindingUtil
import android.arch.lifecycle.Observer
import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.support.design.widget.CoordinatorLayout
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import com.dongnh.chappiebotnewfeed.Adapter.AdapterListView
import com.dongnh.chappiebotnewfeed.Model.Feed
import android.R.attr.fragment
import android.support.v4.app.FragmentActivity

class MainFragment : Fragment() {

    private var errorSnackbar: Snackbar? = null
    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Set up view of RecyclerView
        binding.dataList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        // Change to detail fragment
        viewModel.adapterListView.setOnItemClickListener(object : AdapterListView.OnItemClickListener {
            override fun onClick(view: View, data: Feed) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.main_layout, DetailFragment.newInstance())
                    ?.commit()

                Log.e("Click","aaaaaaa")
            }
        })
        binding.viewModel = viewModel
    }

    /**
     * Set up error when no have connection
     */
    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        val layoutParams = errorSnackbar?.view?.layoutParams as CoordinatorLayout.LayoutParams
        val resources = resources
        val pxBottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45f, resources.displayMetrics).toInt()
        layoutParams.setMargins(0, 0, 0, pxBottom)
        errorSnackbar?.view?.layoutParams = layoutParams
        errorSnackbar?.setAction(R.string.string_retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    /**
     * Hide error
     */
    private fun hideError(){
        errorSnackbar?.dismiss()
    }

}
