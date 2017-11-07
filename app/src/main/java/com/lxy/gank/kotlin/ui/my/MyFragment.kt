package com.lxy.gank.kotlin.ui.my

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.detail.ArticleDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.find

/**
 * Created by a on 2017/11/7.
 */
class MyFragment : BaseFragment() {

    override fun visiableToUser() {
    }

    override fun firstVisiableToUser() {
        var tv : TextView = find<TextView>(R.id.tv_my)
        tv.setOnClickListener {
            var intent = Intent(context, ArticleDetailActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }

    override fun initChildBinding() {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun createView(): AnkoContext<Fragment> {
        return UI {
            relativeLayout {
                var  tv = textView {
                    text="2222"
                    width= wrapContent
                    height= matchParent


                }
            }
        }
    }
}