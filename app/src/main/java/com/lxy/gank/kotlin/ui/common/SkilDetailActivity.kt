package com.lxy.gank.kotlin.ui.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.lxy.gank.kotlin.base.BaseActivity
import com.lxy.gank.kotlin.ui.bean.SkilBean
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.textView
import org.jetbrains.anko.webView

/**
 * Created by lxy on 2017/11/24.
 */
class SkilDetailActivity : BaseActivity() {


    lateinit var des: String
    lateinit var bean: SkilBean.Result

    companion object {
        var SKIL_BEAN = "skil_bean"

        fun gotoPage(context: Context, bean: SkilBean.Result) {
            val intent = Intent(context, SkilDetailActivity::class.java)
            intent.putExtra(SKIL_BEAN, bean)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bean: SkilBean.Result = intent.getSerializableExtra(SKIL_BEAN) as SkilBean.Result
        relativeLayout {
            var tv = webView {

            }.loadUrl(bean.url)
        }
    }
}
