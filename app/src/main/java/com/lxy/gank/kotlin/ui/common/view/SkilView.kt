package com.lxy.gank.kotlin.ui.common.view

import com.lxy.gank.kotlin.base.BaseView
import com.lxy.gank.kotlin.ui.bean.SkilBean

/**
 * Created by lxy on 2017/11/26.
 */
interface SkilView : BaseView {

    fun showResult(list: List<SkilBean.Result>)
}