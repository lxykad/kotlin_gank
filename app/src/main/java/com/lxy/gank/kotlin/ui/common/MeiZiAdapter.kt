package com.lxy.gank.kotlin.ui.common

import android.widget.ImageView
import com.bumptech.glide.Glide
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
                ?.setText(R.id.tv_who, item?.who)
        val imageView = holder?.getView<ImageView>(R.id.image_view)
        // val height:Int = (Math.random() * 400 + 400).toInt()
        var height: Int
        val position: Int = holder?.adapterPosition as Int
        when (position % 3) {
            0 -> {
                height = 400
            }
            1 -> {
                height = 450
            }
            2 -> {
                height = 500
            }
            else -> {
                height = 0
            }
        }
        val params = imageView?.layoutParams
        params?.height = height
        imageView?.layoutParams = params

        Glide.with(imageView?.context)
                .load(item?.url)
                .into(imageView)
    }
}