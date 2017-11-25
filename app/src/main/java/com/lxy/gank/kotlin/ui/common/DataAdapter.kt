package com.lxy.gank.kotlin.ui.common

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.ui.bean.SkilBean

/**
 * Created by lxy on 2017/11/22.
 */

class DataQuickAdapter(id: Int, list: List<SkilBean.Result>) : BaseQuickAdapter<SkilBean.Result, BaseViewHolder>(id, list) {

    override fun convert(helper: BaseViewHolder?, item: SkilBean.Result?) {
        helper?.setText(R.id.tv_title, item?.desc)
                ?.setText(R.id.tv_who, item?.who?:"刘欣宇")
                ?.setText(R.id.tv_date, item?.publishedAt)
    }
}