package com.lxy.gank.kotlin.ui.http

import com.lxy.gank.kotlin.ui.bean.MeiZiBean
import com.lxy.gank.kotlin.ui.bean.SkilBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by a on 2017/11/20.
 */
interface ApiService {

    @GET("{type}/{count}/{page}")
    fun loadSkilData(@Path("type") type: String, @Path("count") count: Int, @Path("page") page: Int): Observable<SkilBean>

    @GET("{type}/{count}/{page}")
    fun loadMeiZiData(@Path("type") type: String, @Path("count") count: Int, @Path("page") page: Int): Observable<MeiZiBean>

}