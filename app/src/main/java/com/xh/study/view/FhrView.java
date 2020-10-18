package com.xh.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;

public class FhrView extends View {

    private final static String FHR_STR = "胎心率/FHR  bpm";
    private final static String TOCO_STR = "宫缩曲线/TOCO %       胎动";

    private Paint mPaint;
    private Paint mTextPaint;
    private int mWidth, mHeight;
    private int mWindowWidth;

    private int spacingX, spacingY1, spacingY2;
    private int maginX, maginY;

    private Rect mTextRect = new Rect();
    private Rect mNumRect = new Rect();

    private Path mFhrPath = new Path();
    private Path mTocoPath = new Path();
    private int fhrLength, tocoLength;

    //是否在手动滑动
    private boolean isScroll;
    private HorizontalScrollView mParent;

    private Handler mHandler = new Handler();

    public FhrView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStrokeWidth(dip2px(1));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(dip2px(15));

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);//消除锯齿
        mTextPaint.setStrokeWidth(dip2px(1));
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(dip2px(15));

        LogUtils.e(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().density);


        spacingX = dip2px(25);
        spacingY1 = dip2px(17);
        spacingY2 = dip2px(10);
        maginX = dip2px(40);
        maginY = dip2px(30);
        mPaint.getTextBounds(FHR_STR, 0, FHR_STR.length(), mTextRect);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mParent = (HorizontalScrollView) getParent();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWindowWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = 3 * maginY + 15 * spacingY1 + 10 * spacingY2;

        int max = maginX * 2 + spacingX * 40;
        mWidth = Math.max(mWindowWidth, max);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //背景
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xffc3f2eb);
        canvas.drawRect(maginX, maginY + spacingY1 * 4, maginX + spacingX * 40, maginY + spacingY1 * 9, mPaint);

        //文字
        mTextPaint.setColor(0xFFD12020);
        canvas.drawText(FHR_STR, maginX, (maginY + mTextRect.height()) / 2, mTextPaint);
        canvas.drawText(TOCO_STR, maginX, maginY + spacingY1 * 15 + (maginY + mTextRect.height()) / 2, mTextPaint);
        mTextPaint.setColor(0xFF575757);
        //竖线 和数字
        int yLength1 = 15 * spacingY1;
        int yLength2 = 10 * spacingY2;
        int textX = 0;
        int textXY = maginY * 2 + yLength1 + yLength2 + mTextRect.height();//横线文字的y坐标
        for (int i = 0; i < 41; i++) {
            if (i % 6 == 0) {
                mPaint.setColor(0xFF575757);
            } else {
                mPaint.setColor(0xFFAEAEAE);
            }
            int maginLeft = i * spacingX + maginX;
            canvas.drawLine(maginLeft, maginY, maginLeft, maginY + yLength1, mPaint);

            canvas.drawLine(maginLeft, maginY * 2 + yLength1, maginLeft, maginY * 2 + yLength1 + yLength2, mPaint);

//            最下方文字
            if (i % 2 == 0 && i != 0) {
                textX++;
                String drawStr = String.valueOf(textX);
                mTextPaint.getTextBounds(drawStr, 0, drawStr.length(), mNumRect);
                canvas.drawText(drawStr, maginLeft - mNumRect.width() / 2, textXY, mTextPaint);
            }

        }
        //上部横线 和数字
        mPaint.setColor(0xFFAEAEAE);
        int textY = 200;
        for (int i = 0; i < 16; i++) {
            int maginTop = maginY + i * spacingY1;
            if (i == 15) {
                mPaint.setColor(0xFF575757);
                canvas.drawLine(maginX, maginTop, maginX + 40 * spacingX, maginTop, mPaint);
            } else {
                canvas.drawLine(maginX, maginTop, maginX + 40 * spacingX, maginTop, mPaint);
            }
            if (i % 2 == 0) {
                String drawStr = String.valueOf(textY);
                textY -= 20;
                mTextPaint.getTextBounds(drawStr, 0, drawStr.length(), mNumRect);
                canvas.drawText(drawStr, maginX - mNumRect.width() - 8, maginTop + mNumRect.height() / 2, mTextPaint);
            }
        }

//        下部横线和数字
        mPaint.setColor(0xFFAEAEAE);
        textY += 60;
        for (int i = 0; i < 11; i++) {
            int maginTop = maginY * 2 + yLength1 + i * spacingY2;
            if (i == 10) {
                mPaint.setColor(0xFF575757);
                canvas.drawLine(maginX, maginTop, maginX + 40 * spacingX, maginTop, mPaint);
            } else {
                canvas.drawLine(maginX, maginTop, maginX + 40 * spacingX, maginTop, mPaint);
            }
            if (i == 0 || i == 5) {
                String drawStr = String.valueOf(textY);
                textY -= 50;
                mTextPaint.getTextBounds(drawStr, 0, drawStr.length(), mNumRect);
                canvas.drawText(drawStr, maginX - mNumRect.width() - 8, maginTop + mNumRect.height() / 2, mTextPaint);
            }
        }

        //过来的数据
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xffDF2424);
        canvas.drawPath(mFhrPath, mPaint);
        mPaint.setColor(0xff22B528);
        canvas.drawPath(mTocoPath, mPaint);


        if (!isScroll) {
            //滑动的距离+屏幕的宽度的一般，就是当前的中点
            int left = mParent.getScrollX() + (mWindowWidth >> 1);
//            计算你传入数据的进度应该滑动到哪里
            int progress = maginX + Math.max(fhrLength, tocoLength) * spacingX / 120;
//            如果应该在往右边滑动，就滑动到多少距离
            if (progress > left) {
                mParent.scrollTo(progress - (mWindowWidth >> 1), 0);
            }
//            如果滑动的距离大于进度，就是没有显示在屏幕上，那就滑动到进度的中心
            if (mParent.getScrollX() > progress) {
                mParent.scrollTo(progress - (mWindowWidth >> 1), 0);
            }
        }
    }


    public void setData(List<Integer> fhrList, List<Integer> tocoList) {
        if (fhrList != null && fhrList.size() != 0) {
            if (fhrLength == 0) {
                mFhrPath.moveTo(maginX, 15 * spacingY1 * (200 - fhrList.get(0)) / 150 + maginY);
            }
            for (int i = 0; i < fhrList.size(); i++) {
                mFhrPath.lineTo(maginX + spacingX * fhrLength / 120, 15 * spacingY1 * (200 - fhrList.get(i)) / 150 + maginY);
                fhrLength++;
            }
        }

        if (tocoList != null && tocoList.size() != 0) {
            if (tocoLength == 0) {
                mTocoPath.moveTo(maginX,
                        10 * spacingY2 * (100 - tocoList.get(0)) / 100 + maginY * 2 + 15 * spacingY1);
            }
            for (int i = 0; i < tocoList.size(); i++) {
                mTocoPath.lineTo(maginX + spacingX * tocoLength / 120,
                        10 * spacingY2 * (100 - tocoList.get(i)) / 100 + maginY * 2 + 15 * spacingY1);
                tocoLength++;
            }
        }
        invalidate();
    }

    public void setNotAutoScroll() {
        isScroll = true;
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(() -> {
            isScroll = false;
        }, 10 * 1000);
    }


//    private int downX;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        isScroll = true;
//        int down = (int) event.getX();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = down;
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                if (Math.abs(downX - down) > 8) {
//                    isScroll = true;
//                    mHandler.removeCallbacksAndMessages(null);
//                    mHandler.postDelayed(() -> {
//                        isScroll = false;
//                    }, 10 * 1000);
//
//                }
//                mParent.scrollBy(downX - down,0);
//
//
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//
//                break;
//        }
//
//
//        return true;
//    }


    private int dip2px(int dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
