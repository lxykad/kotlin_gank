package com.lxy.gank.kotlin.ui.bean

/**
 * Created by a on 2017/11/23.
 */
data class MeiZiBean(
        val error: Boolean, //false
        val results: List<Result>
) {
    data class Result(
            val _id: String, //5a121895421aa90fe50c021e
            val createdAt: String, //2017-11-20T07:49:41.797Z
            val desc: String, //2017-11-20
            val publishedAt: String, //2017-11-20T12:42:06.454Z
            val source: String, //chrome
            val type: String, //福利
            val url: String, //http://7xi8d6.com1.z0.glb.clouddn.com/20171120074925_ZXDh6l_joanne_722_20_11_2017_7_49_16_336.jpeg
            val used: Boolean, //true
            val who: String //daimajia
    )

}

