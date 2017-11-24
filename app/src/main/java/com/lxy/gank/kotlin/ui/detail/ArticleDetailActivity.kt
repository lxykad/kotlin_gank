package com.lxy.gank.kotlin.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.lxy.gank.kotlin.base.BaseActivity
import com.lxy.gank.kotlin.ui.common.LayoutUI
import org.jetbrains.anko.*

class ArticleDetailActivity : BaseActivity() {

    var mEditText: EditText? = null
    var mAvatar: ImageView? = null

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LayoutUI().setContentView(this)

//        relativeLayout {
//            mEditText = editText {
//                hint = "haha"
//                id = 1
//                textSize = 20f
//
//            }.lparams {
//                width = matchParent
//                height = wrapContent
//            }
//
//            var bt = button {
//
//                text = "click"
//
//                width = wrapContent
//                height = wrapContent
//
//                setOnClickListener { showClick() }
//
//            }.lparams {
//                below(1)
//            }
//
//            //
//            mAvatar = imageView {
//
//            }.lparams {
//                width = 80
//                height = 80
//                centerHorizontally()
//                topMargin = 100
//
//            }
//
//
//        }
    }
}
