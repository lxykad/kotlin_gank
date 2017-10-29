package com.lxy.gank.kotlin

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.lxy.gank.kotlin.base.BaseActivity
import com.lxy.gank.kotlin.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    //声明一个允许为null的变量
    protected lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.frameLayout.setOnClickListener {
           // Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
            showToast("toast")
        }

    }

}
