package com.xh.study.view.line;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class MyHScrollView extends HorizontalScrollView {

    private int downX, downY;
    private int moveX;


    public MyHScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = (int) event.getX();
//                downY = (int) event.getY();
//                LogUtils.e("down---" + downX);
//                break;
//            case MotionEvent.ACTION_MOVE:
//
////                if (downX)
//
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                moveX = Math.abs((int) event.getX() - downX);
////                downX = (int) event.getX();
////                downY = (int) event.getY();
//                LogUtils.e("move---" + moveX);
//                break;
//        }
//        LogUtils.e(getScrollX(),getScaleY());

        return super.onTouchEvent(event);
    }
}
