package com.lxy.gank.kotlin.ui.common

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.ui.bean.SkilBean
import kotlinx.android.synthetic.main.list_item_skil.*

/**
 * Created by lxy on 2017/11/22.
 */
class DataAdapter(val list: List<SkilBean.Result>): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
       holder!!.title.text = list.get(position).desc
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return DataAdapter.ViewHolder(TextView(parent?.context))
    }

    override fun getItemCount()=list.size

    class ViewHolder(val title:TextView):RecyclerView.ViewHolder(title)
}


class DataQuickAdapter(id:Int,list: List<SkilBean.Result>):BaseQuickAdapter<SkilBean.Result,BaseViewHolder>(id,list){

    override fun convert(helper: BaseViewHolder?, item: SkilBean.Result?) {
        helper?.setText(R.id.tv_title,item?.desc)
    }


}