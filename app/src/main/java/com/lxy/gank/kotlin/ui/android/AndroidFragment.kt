package com.lxy.gank.kotlin.ui.android

import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.orhanobut.logger.Logger
import java.net.URL
import kotlin.concurrent.thread

/**
 * Created by lxy on 2017/10/28.
 */
class AndroidFragment : BaseFragment() {

    private val mUrl = "http://gank.io/api/data/Android/1/1"

    override fun visiableToUser() {

    }

    override fun firstVisiableToUser() {
        loadData()

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_android
    }

    override fun initChildBinding() {
    }

    fun loadData() {

        thread {
            kotlin.run {
                val json = URL(mUrl).readText()
                Thread.sleep(1000)
                println("json======" + json)
            }
        }


    }

}