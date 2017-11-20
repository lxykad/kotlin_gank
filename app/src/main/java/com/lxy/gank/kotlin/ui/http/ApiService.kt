package com.lxy.gank.kotlin.ui.http

import com.lxy.gank.kotlin.ui.bean.SkilBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by a on 2017/11/20.
 */
interface ApiService {

    @GET("/Android/{count}/{page}/")
    fun loadAndroidData(@Path("count") count: Int,@Path("page") page: Int):Observable<SkilBean>
}