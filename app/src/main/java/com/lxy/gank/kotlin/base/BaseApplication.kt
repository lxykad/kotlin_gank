package com.lxy.gank.kotlin.base

import android.app.Application
import com.lxy.gank.kotlin.ui.http.ApiService
import com.lxy.gank.kotlin.ui.http.HttpHelper
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by lxy on 2017/10/28.
 *
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    companion object {
        
        fun getApiService(): ApiService {
            val apiService = Retrofit.Builder()
                    .client(getOkhttpClient()?.build())
                    .baseUrl(HttpHelper.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ApiService::class.java)

            return apiService
        }

        fun getOkhttpClient(): OkHttpClient.Builder? {

            val builder = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)

            val log = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Logger.d("http_response=====$it")
            })
            log.level = HttpLoggingInterceptor.Level.BODY

            return builder
        }

    }

}