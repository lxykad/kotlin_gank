package com.lxy.gank.kotlin

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.lxy.gank.kotlin.base.BaseActivity
import com.lxy.gank.kotlin.databinding.ActivityMainBinding
import com.lxy.gank.kotlin.ui.android.AndroidFragment
import com.lxy.gank.kotlin.ui.h5.H5Fragment
import com.lxy.gank.kotlin.ui.ios.IosFragment
import com.lxy.gank.kotlin.ui.meizi.MeiZiFragment
import com.lxy.gank.kotlin.ui.my.MyFragment

class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mTabs = arrayOf("android", "ios", "前端", "妹纸", "拓展")
    private var mFragments: ArrayList<Fragment>? = null

    private val mIconUnselectIds = intArrayOf(R.drawable.ic_home_unselect,
            R.drawable.ic_android_unselect,
            R.drawable.ic_phone_unselect,
            R.drawable.ic_my_unselect,
            R.drawable.ic_my_unselect)

    private val mIconSelectIds = intArrayOf(R.drawable.ic_home_select,
            R.drawable.ic_android_select,
            R.drawable.ic_phone_select,
            R.drawable.ic_my_select,
            R.drawable.ic_my_select)

    private val mTabEntities = java.util.ArrayList<CustomTabEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initData()
    }

    private fun initData() {
        mFragments = ArrayList()
        mFragments!!.add(AndroidFragment())
        mFragments!!.add(IosFragment())
        mFragments!!.add(H5Fragment())
        mFragments!!.add(MeiZiFragment())
        mFragments!!.add(MyFragment())

        for (i in mTabs.indices) {
            mTabEntities.add(TabEntity(mTabs[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }

        mBinding.tabLayout.setTabData(mTabEntities, this, R.id.frame_layout, mFragments)
    }

}
