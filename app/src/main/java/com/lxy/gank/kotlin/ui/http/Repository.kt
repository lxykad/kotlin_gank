package com.lxy.gank.kotlin.ui.http

import io.rx_cache2.internal.RxCache
import io.victoralbertos.jolyglot.GsonSpeaker
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File

/**
 * Created by a on 2017/12/1.
 * rxcache 缓存类
 */
class Repository(cacheDir: File) {

    lateinit var cacheProvides: CacheProviders
    lateinit var apiService: ApiService

    init {
        cacheProvides = RxCache.Builder()
                .persistence(cacheDir, GsonSpeaker())
                .using(CacheProviders::class.java)

        apiService = Retrofit.Builder()
                .baseUrl(HttpHelper.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

    companion object {

        fun getInstance(cacheDir: File): Repository {
            return Repository(cacheDir)
        }

    }
}