package com.lxy.gank.kotlin.ui.meizi

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lxy.gank.kotlin.R

class ImagePreviewActivity : AppCompatActivity() {



    companion object {
        fun gotoPage(context: Context,url:String) {
            var intent = Intent(context, ImagePreviewActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)
    }
}
