package com.lxy.gank.kotlin.ui.meizi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_image_preview.*

class ImagePreviewActivity : BaseActivity(){

    lateinit var url: String

    companion object {
        val IMG_URL: String = "img_url"
        fun gotoPage(context: Context, url: String,imageView: ImageView) {
            var intent = Intent(context, ImagePreviewActivity::class.java)
            intent.putExtra(IMG_URL, url)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)

        url = intent.getStringExtra(IMG_URL)
        Glide.with(this)
                .load(url)
                .into(drag_view)

    }

}
