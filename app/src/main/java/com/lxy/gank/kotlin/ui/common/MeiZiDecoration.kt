package com.lxy.gank.kotlin.ui.common

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by lxy on 2017/11/23.
 */
class MeiZiDecoration(var space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect?.left = space;
        outRect?.right = space;
        outRect?.bottom = space;
        if (parent?.getChildAdapterPosition(view) == 0) {
            outRect?.top = space;
        }
    }
}