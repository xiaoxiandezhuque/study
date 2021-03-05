package com.xh.study.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;

public class AutoViewGroupView extends ViewGroup {
    public AutoViewGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        View view = getChildAt(0);

        LogUtils.e(MeasureSpec.getMode(getChildMeasureSpec(widthMeasureSpec, 0, view.getLayoutParams().width)),
                MeasureSpec.getMode(getChildMeasureSpec(heightMeasureSpec, 0, view.getLayoutParams().height)),
                MeasureSpec.AT_MOST,
                MeasureSpec.UNSPECIFIED,
                MeasureSpec.EXACTLY);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
