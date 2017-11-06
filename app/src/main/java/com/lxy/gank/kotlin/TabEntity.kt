package com.lxy.gank.kotlin

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Created by a on 2017/10/30.
 */
class TabEntity(title: String, selectedIcon: Int, unSelectedIcon: Int) : CustomTabEntity {

    var title: String = ""
    var selectedIcon: Int = 0
    var unSelectedIcon: Int = 0

    init{
        this.title = title
        this.selectedIcon = selectedIcon
        this.unSelectedIcon = unSelectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }

}