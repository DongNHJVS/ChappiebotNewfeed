package com.dongnh.chappiebotnewfeed

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.dongnh.chappiebotnewfeed.API.CallAPI
import com.dongnh.chappiebotnewfeed.Adapter.AdapterListView
import com.dongnh.chappiebotnewfeed.Base.BaseViewModel
import com.dongnh.chappiebotnewfeed.Model.ListFeed
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : BaseViewModel() {
    @Inject
    lateinit var callApi: CallAPI
    val adapterListView: AdapterListView = AdapterListView()
    val errorClickListener = View.OnClickListener { loadAllFeed() }
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    init{
        loadAllFeed()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    /**
     * Get data
     */
    private fun loadAllFeed(){
        subscription = callApi.getAllData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveNewFeedListStart() }
            .doOnTerminate { onRetrieveNewFeedListFinish() }
            .doOnError { Log.e("Error :", it.message, it.cause) }
            .subscribe(
                { result -> onRetrieveNewFeedListSuccess(result) },
                { onRetrieveNewFeedListError() }
            )
    }

    /**
     * Start get
     */
    private fun onRetrieveNewFeedListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    /**
     * Get complete, hide dialog loading
     */
    private fun onRetrieveNewFeedListFinish(){
        loadingVisibility.value = View.GONE
    }

    /**
     * Handel data
     */
    private fun onRetrieveNewFeedListSuccess(feeds: ListFeed){
        adapterListView.updateNewsFeedList(feeds)
    }

    /**
     * Show error get data
     */
    private fun onRetrieveNewFeedListError(){
        errorMessage.value = R.string.string_get_data_error
    }
}
