package com.lxy.gank.kotlin.ui.meizi

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseApplication
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.MeiZiBean
import com.lxy.gank.kotlin.ui.common.MeiZiAdapter
import com.lxy.gank.kotlin.ui.common.MeiZiDecoration
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_android.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by lxy on 2017/10/28.
 */
class MeiZiFragment : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private var page: Int = 0
    private lateinit var mList: MutableList<MeiZiBean.Result>
    private lateinit var adapter: MeiZiAdapter

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
        mList = mutableListOf()
        recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = MeiZiAdapter(R.layout.list_item_meizi, mList)
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        recycler_view.addItemDecoration(MeiZiDecoration(15))
        recycler_view.adapter = adapter

        adapter!!.setOnItemClickListener { adapter, view, position ->
            val bean = mList?.get(position)
            ImagePreviewActivity.gotoPage(context, bean.url)
        }
        adapter!!.setOnLoadMoreListener(this, recycler_view)
        refresh_layout.setOnRefreshListener(this)
    }

    fun loadData() {
        BaseApplication.getApiService()
                .loadMeiZiData("福利", 10, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MeiZiBean> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: MeiZiBean) {
                        setList(t.results)
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                        toast("err")
                    }

                })
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


    fun setList(list: List<MeiZiBean.Result>) {

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

}
