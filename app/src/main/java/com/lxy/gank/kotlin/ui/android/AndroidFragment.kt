package com.lxy.gank.kotlin.ui.android

import android.content.Intent
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.httpGet
import com.lxy.gank.kotlin.R
import com.lxy.gank.kotlin.base.BaseFragment
import com.lxy.gank.kotlin.ui.bean.SkilBean
import com.lxy.gank.kotlin.ui.common.QuickAdapter
import com.lxy.gank.kotlin.ui.common.SkilAdapter
import org.jetbrains.anko.support.v4.find

/**
 * Created by lxy on 2017/10/28.
 */
class AndroidFragment : BaseFragment() {

    private val mUrl = "http://gank.io/api/data/Android/50/1"

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

}

private fun FuelManager.request(get: Method, mUrl: String, listOf: List<Pair<String, String>>) {


}

