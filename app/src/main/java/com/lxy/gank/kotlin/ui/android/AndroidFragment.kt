package com.lxy.gank.kotlin.ui.android

import android.support.v4.util.Pair
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.httpGet
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.SkilBean
import kotlinx.android.synthetic.main.fragment_android.*

/**
 * Created by lxy on 2017/10/28.
 */
class AndroidFragment : BaseFragment() {

    private val mUrl = "http://gank.io/api/data/Android/1/3"

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

    fun loadData() {

        mUrl.httpGet()
                .responseObject(SkilBean.Deserializer()){
                    request, response, result ->

                    val (user,err)= result
                    tv_android.setText(user?.results?.get(0)?.desc)
                }
    }

}

private fun FuelManager.request(get: Method, mUrl: String, listOf: List<Pair<String, String>>) {


}

