package com.lxy.gank.kotlin.ui.h5

import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseApplication
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.SkilBean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by lxy on 2017/10/28.
 */
class H5Fragment: BaseFragment() {
    override fun visiableToUser() {
    }

    override fun firstVisiableToUser() {
        BaseApplication.getApiService()
                .loadSkilData("前端",10,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<SkilBean>{
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: SkilBean) {
                        println("h5========suc==${t.results.size}")
                    }

                    override fun onError(e: Throwable) {
                        println("h5========suc==${e.toString()}")
                    }

                    override fun onComplete() {
                    }
                })
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_h5
    }

    override fun initChildBinding() {
    }

    fun setRecyclerView(){

    }

}