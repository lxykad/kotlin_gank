package com.lxy.gank.kotlin.common

import android.content.Context
import android.widget.Toast

/**
 * Created by lxy on 2017/11/16.
 */
class Extension {

    fun Context.toast(msg: String, lenght: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, lenght).show()
    }

}