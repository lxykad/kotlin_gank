package com.lxy.gank.kotlin.ui.common

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.lxy.gank.kotlin.ui.bean.SkilBean

/**
 * Created by lxy on 2017/11/13.
 */
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

