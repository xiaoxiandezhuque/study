package com.xh.study.view.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LineView extends View {

    private int spacing;


    private OnSelectListener mOnSelectListener;

    private Paint mPaint;

    private int mWidth, mHeight;

    private int mWindowWidth;

    private List<Integer> mDrawList = new ArrayList<>();
    private int mTime;
    private double mHowMuchData;

    private int mPlayTime;

    private int downX, moveX;
    private int upX;

    private boolean isSelect;
    private boolean isDownSelect;


    private boolean isPlaying;

    private HorizontalScrollView mParent;

    private int drawingTime;


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
        mWindowWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getMeasuredHeight();

        int max = spacing * mDrawList.size();
        mWidth = Math.max(mWindowWidth, max);
        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long star = System.currentTimeMillis();
        if (isDownSelect) {
            mPaint.setColor(0xff0000ff);
            canvas.drawRect(downX, 0, moveX, mHeight, mPaint);

        }


        mPaint.setColor(0xffe6e6e6);
        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mPaint);
        int progress = (int) ((mPlayTime + drawingTime) * mHowMuchData);

        int left = mParent.getScrollX() + (mWindowWidth >> 1);
        if (isPlaying) {
            if (progress > left) {
                mParent.scrollTo(progress - (mWindowWidth >> 1), 0);
            }
            if (mParent.getScrollX() > progress) {
                mParent.scrollTo(progress - (mWindowWidth >> 1), 0);
            }
        }

        for (int i = 0; i < mDrawList.size(); i++) {
            int start = spacing * i;
            if (progress < i) {
                mPaint.setColor(0xffe6e6e6);//
            } else {
                mPaint.setColor(0xff82F5B9);//以播放
            }

            if (mDrawList.get(i) > 1) {
                canvas.drawLine(start, (mHeight - mDrawList.get(i)) >> 1, start, mHeight - (mHeight - mDrawList.get(i)) / 2, mPaint);
            }
        }
        drawingTime = (int) (System.currentTimeMillis() - star);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mParent = (HorizontalScrollView) getParent();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isPlaying) {
            return true;
        }
        if (!isSelect) {
            return false;
        }
        int down = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = down;
                isDownSelect = true;

                break;
            case MotionEvent.ACTION_MOVE:
                int move = down - mParent.getScrollX();
                if (move > mWindowWidth - 50) {
                    mParent.scrollBy(8, 0);
                } else if (move < 50) {
                    mParent.scrollBy(-8, 0);
                }

                moveX = down;

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                upX = down;

                if (Math.abs(upX - downX) > 8) {
                    int starTime = 0;
                    int endTime = 0;
                    if (downX < upX) {
                        starTime = (int) (downX / spacing / mHowMuchData);
                        endTime = (int) (upX / spacing / mHowMuchData);
                    } else {
                        starTime = (int) (upX / spacing / mHowMuchData);
                        endTime = (int) (downX / spacing / mHowMuchData);
                    }
                    if (starTime < endTime && mOnSelectListener != null) {
                        mOnSelectListener.select(starTime, endTime);
                    }

                }

                break;
        }


        return true;
    }

    //设置数据和总时间
    public void setDecibel(List<Integer> decibel, int time) {
        post(() -> {
            mTime = time;
            mHowMuchData = decibel.size() / (double) time;
            for (int i = 0; i < decibel.size(); i++) {
                int value = (int) getValue(decibel.get(i));
                mDrawList.add(value);
            }
            requestLayout();
            invalidate();
        });

    }

    //设置当前的播发时间，要一直调用
    public void setPlayTime(int time) {

        if (time > mTime) {
            return;
        }
        mPlayTime = time;
        invalidate();
    }

    //设置是否在播发，确定控件是否可以滑动
    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

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


    private double getValue(double decibel) {
        double y;
        if (decibel <= -15) {
            y = new Random().nextInt(2);
        } else {
            decibel = (decibel) / 10;
            y = 3.5 * decibel * decibel - 32;
        }
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
