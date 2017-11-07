package com.lxy.gank.kotlin.utils

import android.content.Context
import android.view.View
import android.widget.Toast


/**
 * Created by a on 2017/11/7.
 */
object Utils {
    fun Context.tos(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    val View.ctx: Context
        get() = context
}