package com.dongnh.chappiebotnewfeed.Injection.Component

import com.dongnh.chappiebotnewfeed.BlankViewModel
import com.dongnh.chappiebotnewfeed.DetailViewModel
import com.dongnh.chappiebotnewfeed.Injection.Module.NetworkModule
import com.dongnh.chappiebotnewfeed.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified BlankViewModel.
     * @param blankViewModel BlankViewModel in which to inject the dependencies
     */
    fun inject(blankViewModel: BlankViewModel)
    /**
     * Injects required dependencies into the specified DetailViewModel.
     * @param detailViewModel DetailViewModel in which to inject the dependencies
     */
    fun inject(detailViewModel: DetailViewModel)
    /**
     * Injects required dependencies into the specified MainViewModel.
     * @param mainViewModel MainViewModel in which to inject the dependencies
     */
    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}