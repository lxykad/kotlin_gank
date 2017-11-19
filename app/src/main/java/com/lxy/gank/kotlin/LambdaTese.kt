package com.lxy.gank.kotlin

import java.io.File

/**
 * Created by lxy on 2017/11/19.
 * kotlin的语法
 */
class LambdaTese {

    fun main(args: Array<String>) {

        val text = File(ClassLoader.getSystemResource("input").path).readText()
        println(text)
    }

    companion object {
        fun sout(type: String) {
            println(type)
        }
    }



}

/**
 * lambda的语法
 * 1、始终在花括号内，实参并没有用括号括起来 而是用箭头把实参和lambda的函数体隔开
 * 2、可以把lambda表达式储存在一个变量中，
 * val sum = { x: Int, y: Int -> x + y }
    println(sum(1,2))



 */
