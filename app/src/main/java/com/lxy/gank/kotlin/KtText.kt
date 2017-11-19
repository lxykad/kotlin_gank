package com.lxy.gank.kotlin


/**
 * Created by lxy on 2017/11/19.
 */

/**
 * 注意：带有val声明的为属性，不带val声明的为普通参数
 * 在构造方法和普通方法sayHello（）里都可以访问hello属性，
 * 而world参数只可以在构造world只可以在构造方法init里访问
 */
enum class Language(val hello: String, world: String) {
    ENGLISH("hello", "haha"),
    CHINESE("你好", "哈哈");


    init {
        print(hello)
    }

    fun sayHello() {
        print(hello)
    }

    // 伴生对象 类似于java里面的静态方法、静态常量
    companion object {
        fun sout(type: String) {
            println(type)
        }
    }

}

// 扩展方法  扩展已存在的枚举类

fun Language.sayByeBye() {
    val bye = when (this) {
        Language.CHINESE -> "bye"
        Language.CHINESE -> "拜拜"
        else -> {
        }
    }
    println(bye)
}

fun main(args: Array<String>) {

    val valueOf = Language.valueOf("english".toUpperCase())
    println(valueOf)
    valueOf.sayByeBye()

}