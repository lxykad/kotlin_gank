package com.lxy.gank.kotlin.ui.ios

import android.content.Intent
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.detail.VideoActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_ios.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by lxy on 2017/10/28.
 */
class IosFragment : BaseFragment() {

    override fun visiableToUser() {
        Logger.d("==========ios======visiableToUser")
    }

    override fun firstVisiableToUser() {
        Logger.d("==========ios======firstVisiableToUser")

        bt.setOnClickListener { v->toast("bt")

            val intent = Intent()
            intent.setClass(context,VideoActivity::class.java)
            intent.putExtra("key","from ios fragment")
            startActivity(intent)

        }

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_ios
    }

    override fun initChildBinding() {
    }
}