package com.lxy.gank.kotlin.ui.ios

import android.support.v7.widget.LinearLayoutManager
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseApplication
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.DataQuickAdapter
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
class IosFragment : BaseFragment() {

    override fun visiableToUser() {

    }

    override fun firstVisiableToUser() {

        loadData()

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_ios
    }

    override fun initChildBinding() {
    }

    fun loadData() {
        BaseApplication.getApiService()
                .loadSkilData("iOS", 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<SkilBean> {
                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: SkilBean) {
                        setList(t.results)
                    }

                })
    }

    fun setList(list: List<SkilBean.Result>) {
        recycler_view.layoutManager = LinearLayoutManager(context)
        val adapter: DataQuickAdapter = DataQuickAdapter(R.layout.list_item_skil, list)
        recycler_view.addItemDecoration(MeiZiDecoration(10))
        recycler_view.adapter = adapter
    }
}

