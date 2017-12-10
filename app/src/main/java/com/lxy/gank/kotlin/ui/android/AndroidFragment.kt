package com.lxy.gank.kotlin.ui.android

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jakewharton.rxbinding2.view.RxView
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.DataQuickAdapter
import com.lxy.gank.kotlin.ui.common.MeiZiDecoration
import com.lxy.gank.kotlin.ui.common.SkilDetailActivity
import com.lxy.gank.kotlin.ui.common.SkilPresenter
import com.lxy.gank.kotlin.ui.common.view.SkilView
import com.lxy.gank.kotlin.ui.search.SearchActivity
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_android.*
import java.util.concurrent.TimeUnit

/**
 * Created by lxy on 2017/10/28.
 */
class AndroidFragment : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener,
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
        return R.layout.fragment_android
    }

    override fun initChildBinding() {

    }

    fun init() {
        tv_title.text = "Android技术"
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

        RxView.clicks(iv_search)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(Consumer {
                    startActivity(Intent(context,SearchActivity::class.java))
                })
    }

    fun setList(list: List<SkilBean.Result>) {

        //  refresh_layout.isEnabled = true
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
        // refresh_layout.isEnabled = false
        page++
        loadData()
    }

    fun loadData() {

        presenter.getData("Android", 10, page)
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

