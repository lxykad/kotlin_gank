package com.lxy.gank.kotlin.ui.bean

import java.io.Serializable

/**
 * Created by a on 2017/11/6.
 */
data class SkilBean(var error: Boolean, var results: List<SkilBean.Result>) : Serializable {

    data class Result(
            val _id: String, //5a0312a9421aa90fe7253615
            val createdAt: String, //2017-11-08T22:20:25.632Z
            val desc: String, //雕虫晓技(一) Android 组件化
            val publishedAt: String, //2017-11-10T08:10:02.838Z
            val source: String, //web
            val type: String, //Android
            val url: String, //https://mp.weixin.qq.com/s?__biz=MzI3MzMzNjgzMA==&mid=2247483674&idx=1&sn=fc93e05445a8fefcde0fdfb2c0145321&chksm=eb25918bdc52189d3ce82d7796ec95105927734d0cf47544126937d3d1255f41002f0cfb3afe&mpshare=1&scene=1&srcid=1106IA3DQP1u3IkOcCVwq1u2&key=5dba587a8b06ccca4ff30ff629f458ed05559774b37f3b9080982d26a7151db05c0324f15551f87029d3a11fee57d8fd51c5622f26b109f6f59a4c6c4ee811a840d7e9862b796b9dbdea8c14a1d32e9f&ascene=0&uin=NjM4NDkwNzgy&devicetype=iMac+MacBookAir7%2C2+OSX+OSX+10.12.4+build(16E195)&version=12010110&nettype=WIFI&fontScale=100&pass_ticket=wtm7nx0Fp2owsbSKcyoiIgV4pVu0Rw0GZicnXakuWo0tyipA8VCHAfpOkJGhp3yb
            val used: Boolean, //true
            val who: String //sloop
    ):Serializable

}