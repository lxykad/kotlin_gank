package com.lxy.gank.kotlin.ui.ios

import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.orhanobut.logger.Logger

/**
 * Created by lxy on 2017/10/28.
 */
class IosFragment : BaseFragment() {
    override fun visiableToUser() {
        Logger.d("==========ios======visiableToUser")
    }

    override fun firstVisiableToUser() {
        Logger.d("==========ios======firstVisiableToUser")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_ios
    }

    override fun initChildBinding() {
    }
}