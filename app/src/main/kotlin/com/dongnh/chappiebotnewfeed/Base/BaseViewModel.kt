package com.dongnh.chappiebotnewfeed.Base

import android.arch.lifecycle.ViewModel
import com.dongnh.chappiebotnewfeed.BlankViewModel
import com.dongnh.chappiebotnewfeed.DetailViewModel
import com.dongnh.chappiebotnewfeed.Injection.Component.DaggerViewModelInjector
import com.dongnh.chappiebotnewfeed.Injection.Component.ViewModelInjector
import com.dongnh.chappiebotnewfeed.Injection.Module.NetworkModule
import com.dongnh.chappiebotnewfeed.MainViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is BlankViewModel -> injector.inject(this)
            is MainViewModel -> injector.inject(this)
            is DetailViewModel -> injector.inject(this)
        }
    }
}