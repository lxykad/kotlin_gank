package com.lxy.gank.kotlin.ui.android

import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.httpGet
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.DataQuickAdapter
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
        // loadData()
        val apiService = Retrofit.Builder()
                .client(getOkhttpClient()?.build())
                .baseUrl(HttpHelper.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)

        apiService.loadSkilData("Android", 10, 1)
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

    override fun getLayoutId(): Int {
        return R.layout.fragment_android
    }

    override fun initChildBinding() {
    }

    fun setList(list: List<SkilBean.Result>) {
        recycler_view.layoutManager = LinearLayoutManager(context)
        val adapter: DataQuickAdapter = DataQuickAdapter(R.layout.list_item_skil, list)
        recycler_view.adapter = adapter
    }

    fun loadData() {

        mUrl.httpGet()
                .responseObject(SkilBean.Deserializer()) { request, response, result ->

                    val (user, err) = result
                    // tv_android.setText(user?.results?.get(0)?.desc)
                    var rv = find<RecyclerView>(R.id.recycler_view)
                    rv.layoutManager = LinearLayoutManager(context)
                    // rv.adapter = QuickAdapter(R.layout.list_item_skil, user!!.results)
                    var adapter = QuickAdapter(R.layout.list_item_skil, user?.results)

                    rv.adapter = SkilAdapter(user!!.results)
                }
    }

    //
    fun getOkhttpClient(): OkHttpClient.Builder? {

        val builder = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)

        val log = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Logger.d("http_response=====$it")
        })
        log.level = HttpLoggingInterceptor.Level.BODY

        return builder
    }

}

private fun FuelManager.request(get: Method, mUrl: String, listOf: List<Pair<String, String>>) {


}

