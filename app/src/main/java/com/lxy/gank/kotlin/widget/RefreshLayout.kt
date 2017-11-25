package com.lxy.gank.kotlin.widget

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import android.widget.Toast
import com.lxy.gank.kotlin.R

/**
 * Created by lxy on 2017/11/25.
 * 如果类有一个主构造函数（无论有无参数），
 * 每个次构造函数需要直接或间接委托给主构造函数，用this关键字
 */
class RefreshLayout : SwipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener {

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    fun init() {
        setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark)
        setOnRefreshListener(this)
    }

    override fun onRefresh() {
       // Toast.makeText(context,"refresh",Toast.LENGTH_SHORT).show()

    }

}