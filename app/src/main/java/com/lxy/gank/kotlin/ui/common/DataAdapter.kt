package com.lxy.gank.kotlin.ui.common

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseApplication
import com.lxy.gank.kotlin.ui.bean.MeiZiBean
import com.lxy.gank.kotlin.ui.bean.SkilBean
import io.reactivex.Observable

/**
 * Created by lxy on 2017/11/22.
 */

class DataQuickAdapter(id: Int, list: List<SkilBean.Result>) : BaseQuickAdapter<SkilBean.Result, BaseViewHolder>(id, list) {

    override fun convert(helper: BaseViewHolder?, item: SkilBean.Result?) {
        helper?.setText(R.id.tv_title, item?.desc)
                ?.setText(R.id.tv_who, item?.who ?: "刘欣宇")
                ?.setText(R.id.tv_date, item?.publishedAt)
    }
}

class SkilModel {

    companion object {

        fun getSkilData(type: String, count: Int, page: Int): Observable<SkilBean> {

            return BaseApplication.getApiService()
                    .loadSkilData(type, count, page)
        }
    }

}

class MeiZiModel {
    companion object {
        fun getMeiZiData(type: String, count: Int, page: Int): Observable<MeiZiBean> {
            return BaseApplication.getApiService()
                    .loadMeiZiData(type, count, page)
        }
    }
}