package com.lxy.gank.kotlin.ui.h5

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseApplication
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.databinding.FragmentH5Binding
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.DataAdapter
import com.lxy.gank.kotlin.ui.common.DataQuickAdapter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by lxy on 2017/10/28.
 */
class H5Fragment : BaseFragment() {

    var mBinding: FragmentH5Binding? = null

    override fun visiableToUser() {

    }

    override fun firstVisiableToUser() {
        loadDadaFromServer()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_h5
    }

    override fun initChildBinding() {
        mBinding = mChildBinding as FragmentH5Binding?
    }

    fun setRecyclerView(list: List<SkilBean.Result>) {

       // var recyclerView = find<RecyclerView>(R.id.recycler_view)


//        mBinding!!.recyclerView.layoutManager = LinearLayoutManager(mBinding!!.recyclerView.context)
//        var adapter: DataAdapter = DataAdapter(list)
//        mBinding!!.recyclerView.adapter = adapter


        mBinding!!.recyclerView.layoutManager = LinearLayoutManager(mBinding!!.recyclerView.context)
        var adapter: DataQuickAdapter = DataQuickAdapter(R.layout.list_item_skil,list)
        mBinding!!.recyclerView.adapter = adapter
    }

    fun loadDadaFromServer() {

        BaseApplication.getApiService()
                .loadSkilData("前端", 20, 1)
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