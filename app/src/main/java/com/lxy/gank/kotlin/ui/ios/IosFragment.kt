package com.lxy.gank.kotlin.ui.ios

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.DataQuickAdapter
import com.lxy.gank.kotlin.ui.common.MeiZiDecoration
import com.lxy.gank.kotlin.ui.common.SkilDetailActivity
import com.lxy.gank.kotlin.ui.common.SkilPresenter
import com.lxy.gank.kotlin.ui.common.view.SkilView
import kotlinx.android.synthetic.main.fragment_ios.*

/**
 * Created by lxy on 2017/10/28.
 */
class IosFragment : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener,
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
        return R.layout.fragment_ios
    }

    override fun initChildBinding() {
    }

    fun init() {
        tv_title.text = "IOS技术"
        mList = mutableListOf()
        recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = DataQuickAdapter(R.layout.list_item_skil, mList)
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
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

    fun loadData() {

        presenter.getData("iOS", 10, page)

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

