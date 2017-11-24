package com.lxy.gank.kotlin.ui.common

import android.view.View
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.ui.detail.ArticleDetailActivity
import org.jetbrains.anko.*

/**
 * Created by a on 2017/11/7.
 */
class LayoutUI : AnkoComponent<ArticleDetailActivity> {

    override fun createView(ui: AnkoContext<ArticleDetailActivity>): View {
        return with(ui) {
            relativeLayout {
                imageView {
                    id = ViewID.IMAGEVIEW_ID
                    setImageResource(R.mipmap.ic_launcher)
                }.lparams {
                    width = 300
                    height = 300
                    centerHorizontally()
                    topMargin = 50
                }
                textView {
                    text = "可爱多"

                }.lparams {
                    below(ViewID.IMAGEVIEW_ID)
                    centerHorizontally()
                    topMargin = 10
                }
            }

        }
    }
}