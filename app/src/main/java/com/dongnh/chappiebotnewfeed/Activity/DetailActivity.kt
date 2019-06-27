package com.dongnh.chappiebotnewfeed.Activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.dongnh.chappiebotnewfeed.DetailViewModel
import com.dongnh.chappiebotnewfeed.Injection.ViewModelFactoryDetail
import com.dongnh.chappiebotnewfeed.R
import com.dongnh.chappiebotnewfeed.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var errorSnackbar: Snackbar? = null
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.hide()

        // Create Binding data
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        viewModel = ViewModelProviders.of(this, ViewModelFactoryDetail(this)).get(DetailViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    /**
     * Set up error when no have connection
     */
    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
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
