package com.lxy.gank.kotlin.ui.android

import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.orhanobut.logger.Logger

/**
 * Created by lxy on 2017/10/28.
 */
class AndroidFragment : BaseFragment() {

    override fun visiableToUser() {
        Logger.d("==========android======visiableToUser")

    }

    override fun firstVisiableToUser() {
        Logger.d("==========android======firstVisiableToUser")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_android
    }

    override fun initChildBinding() {
    }

}