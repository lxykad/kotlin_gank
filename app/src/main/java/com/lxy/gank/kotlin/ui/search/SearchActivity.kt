package com.lxy.gank.kotlin.ui.search

import android.os.Bundle
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val url = "https://lxykad.github.io/"
        web_view.settings.javaScriptEnabled = true
        web_view.loadUrl(url)
    }
}
