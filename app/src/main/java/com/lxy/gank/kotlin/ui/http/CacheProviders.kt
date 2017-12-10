package com.lxy.gank.kotlin.ui.http

import com.lxy.gank.kotlin.ui.bean.MeiZiBean
import com.lxy.gank.kotlin.ui.bean.SkilBean
import io.reactivex.Observable
import io.rx_cache2.LifeCache
import io.rx_cache2.Reply
import java.util.concurrent.TimeUnit

/**
 * Created by a on 2017/12/1
 * rxcache 缓存接口
 */
interface CacheProviders {

    @LifeCache(duration = 1, timeUnit = TimeUnit.SECONDS)
    fun loadSkilData(bean: Observable<SkilBean>): Observable<Reply<SkilBean>>

    @LifeCache(duration = 1, timeUnit = TimeUnit.SECONDS)
    fun loadMeiZiData(bean: Observable<MeiZiBean>): Observable<Reply<MeiZiBean>>

}