package com.lxy.gank.kotlin.ui.common

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.ui.bean.SkilBean

/**
 * Created by lxy on 2017/11/13.
 */

class QuickAdapter(layoutId:Int, list: List<SkilBean.Result>?) : BaseQuickAdapter<SkilBean.Result, BaseViewHolder>(layoutId, list) {

    override fun convert(helper: BaseViewHolder, item: SkilBean.Result) {
//        when (helper.layoutPosition % 3) {
//            0 -> helper.setImageResource(R.id.img, R.mipmap.animation_img1)
//            1 -> helper.setImageResource(R.id.img, R.mipmap.animation_img2)
//            2 -> helper.setImageResource(R.id.img, R.mipmap.animation_img3)
//        }
        helper.setText(R.id.tv_title, "Hoteis")

    }

}

//原生adapter
class SkilAdapter(var list: List<SkilBean.Result>) : RecyclerView.Adapter<SkilAdapter.ViewHoder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        return ViewHoder(TextView(parent.context))
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: ViewHoder?, position: Int) {
        holder!!.textView.setText(list.get(position).desc)
    }

    class ViewHoder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}

