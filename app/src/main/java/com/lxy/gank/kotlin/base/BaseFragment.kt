package com.lxy.gank.kotlin.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.databinding.ContentMultiStatusBinding

/**
 * Created by lxy on 2017/10/28.
 */
abstract class BaseFragment : Fragment() {

    //声明可null
    private var mInflater: LayoutInflater? = null
    // 标识fragment视图已经初始化完毕
    private var mIsViewPrepared: Boolean = false
    // 标识已经触发过懒加载数据
    private var mHasFetchData: Boolean = false
    //延迟初始化
    private lateinit var mBinding: ContentMultiStatusBinding
    protected var mChildBinding: ViewDataBinding? = null

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            lazyFetchDataIfPrepared()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mInflater = inflater
        mBinding = DataBindingUtil.inflate(inflater, R.layout.content_multi_status, container, false)

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setChildContentView()
        initChildBinding()

        mIsViewPrepared = true
    }

    private fun setChildContentView() {

        mChildBinding = DataBindingUtil.inflate(mInflater, getLayoutId(), mBinding.contentLayout, true)

    }

    private fun lazyFetchDataIfPrepared() {

        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (userVisibleHint && !mHasFetchData && mIsViewPrepared) {
            mHasFetchData = true //已加载过数据
            firstVisiableToUser()
        }
        if (userVisibleHint && mIsViewPrepared) {

            visiableToUser()
        }
    }


    protected abstract fun visiableToUser()

    protected abstract fun firstVisiableToUser()

    abstract fun getLayoutId(): Int

    abstract fun initChildBinding()

}