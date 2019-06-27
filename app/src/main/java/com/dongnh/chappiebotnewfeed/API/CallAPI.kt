package com.dongnh.chappiebotnewfeed.API

import com.dongnh.chappiebotnewfeed.Model.Detail
import com.dongnh.chappiebotnewfeed.Model.ListFeed
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface CallAPI {
    /**
     * Get the list of the newfeed from the API
     */
    @GET("/s/fy6ny7syutxl1yd/newsfeed.json?dl=1")
    fun getAllData(): Observable<ListFeed>

    /**
     * Get the detail from the API
     */
    @GET("/s/v83n38kvsm6qw62/detail.json?dl=1")
    fun getDetail(): Observable<Detail>
}
