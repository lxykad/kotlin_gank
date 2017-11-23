package com.lxy.gank.kotlin.ui.meizi

import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_image_preview.*
import kotlinx.android.synthetic.main.fragment_android.*
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

/**
 * Created by lxy on 2017/10/28.
 */
class MeiZiFragment : BaseFragment() {
    override fun visiableToUser() {
    }

    override fun firstVisiableToUser() {
        loadData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_meizi
    }

    override fun initChildBinding() {
    }

    fun loadData() {
        BaseApplication.getApiService()
                .loadMeiZiData("福利", 10, 1)
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

    fun setList(list: List<MeiZiBean.Result>) {
        recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.addItemDecoration(MeiZiDecoration(20))

        var adapter: MeiZiAdapter = MeiZiAdapter(R.layout.list_item_meizi, list)
        recycler_view.adapter = adapter

        adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val bean = list.get(position)
            var intent:Intent = Intent(view.context,ImagePreviewActivity::class.java)
            startActivity(intent)
        }
    }

}
