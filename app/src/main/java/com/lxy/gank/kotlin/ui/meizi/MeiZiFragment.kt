package com.lxy.gank.kotlin.ui.meizi

import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.orhanobut.logger.Logger

/**
 * Created by lxy on 2017/10/28.
 */
class MeiZiFragment : BaseFragment() {
    override fun visiableToUser() {
        Logger.d("==========meizi======visiableToUser")
    }

    override fun firstVisiableToUser() {
        Logger.d("==========meizi======firstVisiableToUser")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_meizi
    }

    override fun initChildBinding() {
    }

}
