package com.xh.study.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils

class TbNestedScrollView(context: Context, attrs: AttributeSet?) :
    NestedScrollView(context, attrs) {

    private var topView: View? = null
    private var contentView: ViewGroup? = null


    override fun onFinishInflate() {
        super.onFinishInflate()
        topView = (getChildAt(0) as ViewGroup).getChildAt(0)
        contentView = (getChildAt(0) as ViewGroup).getChildAt(2) as ViewGroup

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val layoutParams = contentView?.layoutParams
        layoutParams?.height = measuredHeight - 1
        contentView?.layoutParams = layoutParams
        LogUtils.e("onMeasure")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        LogUtils.e("onLayout")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {

//        如果活动向下并且头部的view显示出来了  那么就先滑动头部
        val showTop = dy > 0 && scrollY < (topView?.height ?: 0)
        if (showTop) {
            val scrollDy = Math.min(dy, (topView?.height ?: 0) - scaleY.toInt())
            scrollBy(0, scrollDy)
            consumed[1] = scrollDy
        }
    }

    override fun fling(velocityY: Int) {
        super.fling(velocityY)
        LogUtils.e("fling---    $velocityY")
        if (velocityY > 0) {
            contentView?.let { getChildRecyclerView(it) }?.fling(0, velocityY)
        }
    }

    private fun getChildRecyclerView(viewGroup: ViewGroup): RecyclerView? {
        for (i in 0 until viewGroup.childCount) {
            val view = viewGroup.getChildAt(i)
            if (view is RecyclerView) {
                return viewGroup.getChildAt(i) as RecyclerView
            } else if (viewGroup.getChildAt(i) is ViewGroup) {
                val childRecyclerView: ViewGroup? =
                    getChildRecyclerView(viewGroup.getChildAt(i) as ViewGroup)
                if (childRecyclerView is RecyclerView) {
                    return childRecyclerView
                }
            }
            continue
        }
        return null
    }

}