package com.lxy.gank.kotlin.ui.common

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.ui.bean.MeiZiBean

/**
 * Created by a on 2017/11/23.
 */
class MeiZiAdapter(resId: Int, list: List<MeiZiBean.Result>) : BaseQuickAdapter<MeiZiBean.Result, BaseViewHolder>(resId, list) {

    override fun convert(holder: BaseViewHolder?, item: MeiZiBean.Result?) {
        holder?.setText(R.id.tv_title, item?.desc)
    }
}