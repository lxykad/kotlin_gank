package com.lxy.gank.kotlin.ui.android

import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import kotlinx.coroutines.experimental.async
import okhttp3.Request
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.uiThread
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

        val json = URL(mUrl).readText()
        onUiThread { println("json======" + json.toString()) }
    }

}

