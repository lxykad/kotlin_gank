package com.lxy.gank.kotlin.ui.my

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.DataQuickAdapter
import com.lxy.gank.kotlin.ui.common.MeiZiDecoration
import com.lxy.gank.kotlin.ui.common.SkilDetailActivity
import com.lxy.gank.kotlin.ui.common.SkilPresenter
import com.lxy.gank.kotlin.ui.common.view.SkilView
import com.lxy.gank.kotlin.ui.detail.ArticleDetailActivity
import kotlinx.android.synthetic.main.fragment_android.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.find

/**
 * Created by a on 2017/11/7.
 */
class MyFragment : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener, SkilView {

    private var page: Int = 1
    private lateinit var mList: MutableList<SkilBean.Result>
    private lateinit var adapter: DataQuickAdapter
    private lateinit var presenter: SkilPresenter

    override fun visiableToUser() {

    }

    override fun firstVisiableToUser() {
        init()
        loadData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }

    override fun initChildBinding() {

    }

    fun init() {
        mList = mutableListOf()
        recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = DataQuickAdapter(R.layout.list_item_skil, mList)
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        recycler_view.addItemDecoration(MeiZiDecoration(15))
        recycler_view.adapter = adapter

        adapter!!.setOnItemClickListener { adapter, view, position ->
            val bean = mList?.get(position)
            SkilDetailActivity.gotoPage(context, bean!!)
        }
        adapter!!.setOnLoadMoreListener(this, recycler_view)
        refresh_layout.setOnRefreshListener(this)

        presenter = SkilPresenter(this)
    }

    fun setList(list: List<SkilBean.Result>) {

        if (refresh_layout.isRefreshing) {
            refresh_layout.isRefreshing = false
        }

        if (page == 1) {
            mList.clear()
            mList.addAll(list)
            adapter.notifyDataSetChanged()

        } else {
            // adapter.loadMoreEnd(true)// 没有分页数据时
            // adapter.loadMoreEnd() //底部显示没有更多数据
            adapter.loadMoreComplete()
            mList.addAll(list)
            adapter.notifyDataSetChanged()

        }
    }

    override fun onRefresh() {
        page = 1
        loadData()
    }

    override fun onLoadMoreRequested() {
        page++
        loadData()
    }

    fun loadData() {

        presenter.getData("拓展资源", 10, page)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun showError(msg: String) {

    }

    override fun showResult(list: List<SkilBean.Result>) {
        setList(list)
    }

}