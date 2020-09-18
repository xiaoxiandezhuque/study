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
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (Math.abs(downX - (int) event.getX()) > 8) {
//                    ((FhrView) getChildAt(0)).setNotAutoScroll();
//                }
//
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//
//
//                break;
//        }


        return super.onTouchEvent(event);
    }
}
