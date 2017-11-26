package com.lxy.gank.kotlin.ui.common

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.lxy.gank.kotlin.base.BaseActivity
import com.lxy.gank.kotlin.ui.bean.SkilBean
import org.jetbrains.anko.*

/**
 * Created by lxy on 2017/11/24.
 */
class SkilDetailActivity : BaseActivity() {

    lateinit var des: String
    lateinit var bean: SkilBean.Result
    lateinit var mProgress: ProgressBar

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
                lparams {
                    width = matchParent
                    height = matchParent
                }

                val settings = settings
                settings.javaScriptEnabled = true
                //
                settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
                settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

                // 优先使用缓存
                settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                //缓存模式如下：
                //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
                //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
                //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
                //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。

                // app 内打开
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        view.loadUrl(url)
                        return true
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        mProgress.visibility = View.GONE
                    }

                    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                        super.onReceivedError(view, request, error)
                    }
                }

            }.loadUrl(bean.url)

            mProgress = progressBar {


            }.lparams {
                centerInParent()
            }

        }
    }
}
