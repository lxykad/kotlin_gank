package com.lxy.gank.kotlin.base

/**
 * Created by lxy on 2017/11/26.
 */
interface BaseView {
    fun showLoading()
    fun dismissLoading()
    fun showError(msg:String)
}