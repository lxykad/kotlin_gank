package com.lxy.gank.kotlin.ui.meizi

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.MeiZiBean
import com.lxy.gank.kotlin.ui.common.MeiZiAdapter
import com.lxy.gank.kotlin.ui.common.MeiZiDecoration
import com.lxy.gank.kotlin.ui.common.MeiZiPresenter
import com.lxy.gank.kotlin.ui.common.view.MeiZiView
import kotlinx.android.synthetic.main.fragment_meizi.*

/**
 * Created by lxy on 2017/10/28.
 */
class MeiZiFragment : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener, MeiZiView {

    private var page: Int = 1
    private lateinit var mList: MutableList<MeiZiBean.Result>
    private lateinit var adapter: MeiZiAdapter
    private lateinit var presenter: MeiZiPresenter

    override fun visiableToUser() {
    }

    override fun firstVisiableToUser() {
        init()
        loadData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_meizi
    }

    override fun initChildBinding() {
    }

    fun init() {
        tv_title.text = "美女"
        mList = mutableListOf()
        recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = MeiZiAdapter(R.layout.list_item_meizi, mList)
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        recycler_view.addItemDecoration(MeiZiDecoration(15))
        recycler_view.adapter = adapter

        adapter!!.setOnItemClickListener { adapter, view, position ->
            val bean = mList?.get(position)

            // ImagePreviewActivity.gotoPage(context, bean.url)
            val img = view.findViewById<ImageView>(R.id.image_view)
            ImageDetailActivity.goToPage(context, bean.url, img)
        }
        adapter!!.setOnLoadMoreListener(this, recycler_view)
        refresh_layout.setOnRefreshListener(this)

        presenter = MeiZiPresenter(this)
    }

    fun loadData() {
        presenter.getMeiZiData("福利", 12, page)
    }

    override fun onRefresh() {
        page = 1
        loadData()
    }

    override fun onLoadMoreRequested() {
        page++
        loadData()
    }

    fun setList(list: List<MeiZiBean.Result>) {

        if (refresh_layout.isRefreshing) {
            refresh_layout.isRefreshing = false
        }

        if (page == 1) {
            mList.clear()
            mList.addAll(list)
            adapter.notifyDataSetChanged()

        } else {
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

    override fun showResult(list: List<MeiZiBean.Result>) {
        setList(list)
    }
}
