package com.lxy.gank.kotlin.common

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by lxy on 2017/11/16.
 */
class Extension {

    fun Context.toast(msg: String, lenght: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, lenght).show()
    }

    fun ViewGroup.toast(msg: String, lenght: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this.context, msg, lenght).show()
    }

}