package com.lxy.gank.kotlin.ui.android

import android.support.v7.widget.LinearLayoutManager
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseApplication
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.DataQuickAdapter
import com.lxy.gank.kotlin.ui.common.MeiZiDecoration
import com.lxy.gank.kotlin.ui.common.SkilDetailActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_android.*

/**
 * Created by lxy on 2017/10/28.
 */
class AndroidFragment : BaseFragment() {

    private val mUrl = "http://gank.io/api/data/Android/10/1"

    override fun visiableToUser() {

    }

    override fun firstVisiableToUser() {
        loadData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_android
    }

    override fun initChildBinding() {

    }

    fun setList(list: List<SkilBean.Result>) {
        recycler_view.layoutManager = LinearLayoutManager(context)
        val adapter: DataQuickAdapter = DataQuickAdapter(R.layout.list_item_skil, list)
        recycler_view.addItemDecoration(MeiZiDecoration(15))
        recycler_view.adapter = adapter

        adapter.setOnItemClickListener { adapter, view, position ->
            val bean = list.get(position)
            SkilDetailActivity.gotoPage(context, bean)
        }
    }

    fun loadData() {

        BaseApplication.getApiService()
                .loadSkilData("Android", 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<SkilBean> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onComplete() {

                    }

                    override fun onNext(t: SkilBean) {
                        println("res========suc===${t.results.size}")
                        setList(t.results)
                    }

                    override fun onError(e: Throwable) {
                        println("res========err")
                    }
                })
    }
}

