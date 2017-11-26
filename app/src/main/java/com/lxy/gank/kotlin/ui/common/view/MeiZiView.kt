package com.lxy.gank.kotlin.ui.common.view

import com.lxy.gank.kotlin.base.BaseView
import com.lxy.gank.kotlin.ui.bean.MeiZiBean

/**
 * Created by lxy on 2017/11/26.
 */
interface MeiZiView :BaseView{

    fun showResult(list: List<MeiZiBean.Result>)
}