package com.dongnh.chappiebotnewfeed

import android.arch.lifecycle.MutableLiveData
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import com.dongnh.chappiebotnewfeed.API.CallAPI
import com.dongnh.chappiebotnewfeed.Base.BaseViewModel
import com.dongnh.chappiebotnewfeed.Model.Detail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DetailViewModel : BaseViewModel() {
    @Inject
    lateinit var callApi: CallAPI
    val errorClickListener = View.OnClickListener { loadDetailFeed() }
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    // View in layout
    // Title
    private val stringTitle = MutableLiveData<String>()
    // Description
    private val stringDescription = MutableLiveData<String>()
    // Date published
    private val datePublished = MutableLiveData<String>()



    private lateinit var subscription: Disposable

    init{
        loadDetailFeed()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    /**
     * Get data
     */
    private fun loadDetailFeed(){
        subscription = callApi.getDetail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDetailListStart() }
            .doOnTerminate { onRetrieveDetailListFinish() }
            .doOnError { Log.e("Error :", it.message, it.cause) }
            .subscribe(
                { result -> onRetrieveDetailListSuccess(result) },
                { onRetrieveNewFeedListError() }
            )
    }

    /**
     * Start get
     */
    private fun onRetrieveDetailListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    /**
     * Get complete, hide dialog loading
     */
    private fun onRetrieveDetailListFinish(){
        loadingVisibility.value = View.GONE
    }

    /**
     * Handel data
     */
    private fun onRetrieveDetailListSuccess(detail: Detail){
        this.bind(detail)
    }

    /**
     * Show error get data
     */
    private fun onRetrieveNewFeedListError(){
        errorMessage.value = R.string.string_get_data_error
    }

    /**
     * Binding data to view
     */
    fun bind(detail: Detail){
        stringTitle.value = detail.title
        stringDescription.value = detail.description
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
        val time = sdf.parse(detail.published_date).getTime()
        val now = System.currentTimeMillis()

        val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.WEEK_IN_MILLIS)
        datePublished.value = detail.publisher?.name + " * " + ago
    }

    fun getStringTitle(): MutableLiveData<String> {
        return stringTitle
    }

    fun getStringDescription(): MutableLiveData<String> {
        return stringDescription
    }

    fun getDatePublished(): MutableLiveData<String> {
        return datePublished
    }
}
