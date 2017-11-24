package com.lxy.gank.kotlin.ui.android

import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.httpGet
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseApplication
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.DataQuickAdapter
import com.lxy.gank.kotlin.ui.common.MeiZiDecoration
import com.lxy.gank.kotlin.ui.common.QuickAdapter
import com.lxy.gank.kotlin.ui.common.SkilAdapter
import com.lxy.gank.kotlin.ui.http.ApiService
import com.lxy.gank.kotlin.ui.http.HttpHelper
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_android.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.support.v4.find
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
        recycler_view.addItemDecoration(MeiZiDecoration(10))
        recycler_view.adapter = adapter
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

