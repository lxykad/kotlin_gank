package com.lxy.gank.kotlin.ui.h5

import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.orhanobut.logger.Logger

/**
 * Created by lxy on 2017/10/28.
 */
class H5Fragment: BaseFragment() {
    override fun visiableToUser() {
        Logger.d("==========h5======visiableToUser")
    }

    override fun firstVisiableToUser() {
        Logger.d("==========h5======firstVisiableToUser")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_h5
    }

    override fun initChildBinding() {
    }

}