package com.lxy.gank.kotlin.ui.search

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import com.lxy.gank.kotlin.base.BaseActivity
import com.lxy.gank.kotlin.ui.bean.SkilBean
import org.jetbrains.anko.*

class SearchActivity : BaseActivity() {

    val url = "https://lxykad.github.io/"
    lateinit var mProgress: ProgressBar

    companion object {

        fun gotoPage(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            var tv = webView {
                lparams {
                    width = matchParent
                    height = matchParent
                }

                val settings = settings
              //  settings.javaScriptEnabled = true
                //
               // settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
             //   settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setUseWideViewPort(true);//设置webview推荐使用的窗口
                settings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
                settings.setDisplayZoomControls(false);//隐藏webview缩放按钮
                settings.setJavaScriptEnabled(true); // 设置支持javascript脚本
                settings.setAllowFileAccess(true); // 允许访问文件
                settings.setBuiltInZoomControls(true); // 设置显示缩放按钮
                settings.setSupportZoom(true); // 支持缩放




                // 优先使用缓存
                settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

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

            }.loadUrl(url)

            mProgress = progressBar {


            }.lparams {
                centerInParent()
            }

        }
    }
}
