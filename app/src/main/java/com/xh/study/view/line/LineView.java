package com.xh.study.view.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LineView extends View {

    private int spacing;


    private OnSelectListener mOnSelectListener;

    private Paint mPaint;

    private int mWidth, mHeight;

    private int mWindowWidth;

    //当前段落的宽度
    private int mParagraphWidth;

    private List<Integer> mDrawList = new ArrayList<>();
    private List<Integer> mTimeList = new ArrayList<>();
    private int mTotleTime;
    private double mHowMuchData;

    private int mPlayTime;
    private int mStartTime;
    private int mEndTime;


    private int downX, moveX;
    private int upX;

    private boolean isSelect;
    private boolean isDownSelect;


    private HorizontalScrollView mParent;


    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        spacing = 1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mTotleTime == 0) {
            return;
        }
        mWindowWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getMeasuredHeight();

        mParagraphWidth = spacing * mDrawList.size() * (mEndTime - mStartTime) / mTotleTime;
        mWidth = Math.max(mWindowWidth, mParagraphWidth);
        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        long star = System.currentTimeMillis();
        if (mTotleTime == 0) {
            return;
        }
        if (isDownSelect) {
            mPaint.setColor(0xff0000ff);
            if (downX > moveX) {
                canvas.drawRect(moveX, 0, downX, mHeight, mPaint);
            } else {
                canvas.drawRect(downX, 0, moveX, mHeight, mPaint);
            }
        }
        mPaint.setColor(0xffe6e6e6);
        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mPaint);
        int startList = Math.min((int) (mDrawList.size() / (double) mTotleTime * mStartTime), mDrawList.size() - 1);
        int endList = Math.min((int) (mDrawList.size() / (double) mTotleTime * mEndTime), mDrawList.size() - 1);

        int maginLeft = mWidth > mParagraphWidth ? (mWidth - mParagraphWidth) / 2 : 0;
        for (int i = startList; i <= endList; i++) {
            int start = maginLeft + spacing * (i - startList);
            mPaint.setColor(0xff82F5B9);//以播放
            if (mDrawList.get(i) > 1) {
                canvas.drawLine(start, (mHeight - mDrawList.get(i)) >> 1, start, mHeight - (mHeight - mDrawList.get(i)) / 2, mPaint);
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mParent = (HorizontalScrollView) getParent();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int down = (int) event.getX();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = down;
//                isDownSelect = true;
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int move = down - mParent.getScrollX();
//                if (move > mWindowWidth - 50) {
//                    mParent.scrollBy(8, 0);
//                } else if (move < 50) {
//                    mParent.scrollBy(-8, 0);
//                }
//
//                moveX = down;
//
//                invalidate();
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                upX = down;
//                if (Math.abs(upX - downX) > 8) {
//                    int starTime = 0;
//                    int endTime = 0;
//                    int maginLeft = mWidth > mParagraphWidth ? (mWidth - mParagraphWidth) / 2 : 0;
//                    if (downX < upX) {
//                        int left = Math.max(0, downX - maginLeft);
//
//                        starTime = (int) (left / spacing / mHowMuchData);
//                        if (mParagraphWidth <= upX - maginLeft) {
//                            endTime = mEndTime;
//                        } else {
//                            endTime = (int) ((upX - maginLeft) / spacing / mHowMuchData);
//                        }
//
//                    } else {
//                        int left = Math.max(0, upX - maginLeft);
//                        starTime = (int) (left / spacing / mHowMuchData);
//                        if (mParagraphWidth <= downX - maginLeft) {
//                            endTime = mEndTime;
//                        } else {
//                            endTime = (int) ((downX - maginLeft) / spacing / mHowMuchData);
//                        }
//
//                    }
//                    if (starTime < endTime && mOnSelectListener != null) {
//                        mOnSelectListener.select(starTime + mStartTime, endTime + mStartTime);
//                    }
//                }
//                isDownSelect = false;
//                invalidate();
//
//                break;
//        }
//
//
//        return true;
//    }

    //设置数据和总时间
    public void setDecibel(List<Integer> decibel, List<Integer> timeList) {
        mTimeList.clear();
        mDrawList.clear();
        mTimeList.addAll(timeList);
//        for (int i = timeList.size() - 1; i > 0; i--) {
//            if (timeList.get(i) < timeList.get(i - 1)) {
//                throw new IllegalStateException("传入的数据有问题");
//            }
//        }


        mTotleTime = timeList.get(mTimeList.size() - 1);
        mHowMuchData = decibel.size() / (double) mTotleTime;
        post(() -> {
            //先计算高度
            for (int i = 0; i < decibel.size(); i++) {
                int value = getValue(decibel.get(i));
                mDrawList.add(value);
            }
        });

    }

    //传段数  从0开始
    public void setPlayCount(int count) {
        if (count >= mTimeList.size() || count < 0) {
            return;
        }
        mStartTime = count == 0 ? 0 : mTimeList.get(count - 1);
        mEndTime = mTimeList.get(count);
        post(() -> {
            requestLayout();
            invalidate();
        });

    }


//    //设置当前的播发时间，要一直调用
//    public void setPlayTime(int time) {
//
//        if (time > mTotleTime) {
//            return;
//        }
//        mPlayTime = time;
//        invalidate();
//    }

    //设置是否在播发，确定控件是否可以滑动
//    public void setPlaying(boolean playing) {
//        isPlaying = playing;
//    }

    //清楚选中的背景
    public void clearBg() {
        isDownSelect = false;
        invalidate();
    }

    //   是否可以选择区间
    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    //    选择区间的回调接口
    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }


    private int getValue(int decibel) {
        int y = mHeight * decibel / 100;

        if (y < 1) {
            y = 1;
        }
        if (y > mHeight) {
            y = mHeight;
        }
        return y;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(int dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public interface OnSelectListener {
        void select(int startTime, int endTime);
    }
}
