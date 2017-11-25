package com.lxy.gank.kotlin.ui.h5

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseApplication
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.databinding.FragmentH5Binding
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.DataQuickAdapter
import com.lxy.gank.kotlin.ui.common.MeiZiDecoration
import com.lxy.gank.kotlin.ui.common.SkilDetailActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_android.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by lxy on 2017/10/28.
 */
class H5Fragment : BaseFragment() , BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    var mBinding: FragmentH5Binding? = null
    private var page: Int = 0
    private lateinit var mList: MutableList<SkilBean.Result>
    private lateinit var adapter: DataQuickAdapter

    override fun visiableToUser() {

    }

    override fun firstVisiableToUser() {
        init()
        loadData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_h5
    }

    override fun initChildBinding() {
        mBinding = mChildBinding as FragmentH5Binding?
    }

    fun init() {
        mList = mutableListOf()
        recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = DataQuickAdapter(R.layout.list_item_skil, mList)
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        recycler_view.addItemDecoration(MeiZiDecoration(15))
        recycler_view.adapter = adapter

        adapter!!.setOnItemClickListener { adapter, view, position ->
            val bean = mList?.get(position)
            SkilDetailActivity.gotoPage(context, bean!!)
        }
        adapter!!.setOnLoadMoreListener(this, recycler_view)
        refresh_layout.setOnRefreshListener(this)
    }


    fun setRecyclerView(list: List<SkilBean.Result>) {

        if (refresh_layout.isRefreshing) {
            refresh_layout.isRefreshing = false
        }

        if (page == 0) {
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
        page = 0
        loadData()
    }

    override fun onLoadMoreRequested() {
        // refresh_layout.isEnabled = false
        page++
        loadData()
    }


    fun loadData() {

        BaseApplication.getApiService()
                .loadSkilData("前端", 10, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<SkilBean> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: SkilBean) {
                        println("h5========suc==${t.results.size}")
                        setRecyclerView(t.results)
                    }

                    override fun onError(e: Throwable) {
                        println("h5========suc==${e.toString()}")
                    }

                    override fun onComplete() {
                    }
                })
    }

}