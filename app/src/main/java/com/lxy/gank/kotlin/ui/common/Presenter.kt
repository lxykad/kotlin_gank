package com.lxy.gank.kotlin.ui.common

import com.lxy.gank.kotlin.ui.bean.MeiZiBean
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.view.MeiZiView
import com.lxy.gank.kotlin.ui.common.view.SkilView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by lxy on 2017/11/26.
 */
class SkilPresenter(val view: SkilView) {

    fun getData(type: String, count: Int, page: Int) {
        SkilModel.getSkilData(type, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<SkilBean> {
                    override fun onNext(t: SkilBean) {
                        view.showResult(t.results)
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                })
    }
}

class MeiZiPresenter(val view: MeiZiView) {

    fun getMeiZiData(type: String, count: Int, page: Int) {
        MeiZiModel.getMeiZiData(type, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MeiZiBean> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: MeiZiBean) {
                        view.showResult(t.results)
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }

                })
    }
}