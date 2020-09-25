package com.xh.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class MeasureTextHeightView extends AppCompatTextView {

    private String mText = "我的课堂";//成员变量

    private float mPercent = 0.4f;

    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float mPercent) {
        this.mPercent = mPercent;
        invalidate();//重绘
    }

    public MeasureTextHeightView(Context context) {
        super(context);
    }

    public MeasureTextHeightView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureTextHeightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private boolean isdraw = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isdraw) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
            return;
        }

        drawCenterLineX(canvas);
        drawCenterLineY(canvas);
        //把文字绘制到view的中心
        //文字高度的计算

//        public float ascent;
//        public float bottom;
//        public float descent;
//        public float leading;
//        public float top; 从baseline到文字最顶端的尺寸


        //第二种方式 x种居中
        //底层 黑色
        drawCenterText(canvas);
        //上面一层 红色
        drawCenterText1(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextSize(80);
        paint.setColor(Color.RED);
//        Rect rect = new Rect();
//        paint.getTextBounds(mText, 0, mText.length(), rect);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        int a = (int) (fontMetrics.descent - fontMetrics.ascent);
        float baseline1 = getHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2;
//        float baseline2 = getHeight() / 2 + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.descent;
//        float baseline3 = getHeight() / 2 + rect.height() / 2;
        canvas.drawLine(0, baseline1, getWidth(), baseline1, paint); //基准线

        paint.setColor(Color.BLUE);
        float baseline2 = getHeight() / 2 - (fontMetrics.bottom - fontMetrics.top) / 2; //上部线
        float baseline3 = getHeight() / 2 + (fontMetrics.bottom - fontMetrics.top) / 2;//下部线
        canvas.drawLine(0, baseline2, getWidth(), baseline2, paint);
        canvas.drawLine(0, baseline3, getWidth(), baseline3, paint);
//        canvas.drawLine(0, baseline3, getWidth(), baseline3, paint);
//        float baseline5 = getHeight() / 2 - rect.height() / 2;
//        canvas.drawLine(0, baseline5, getWidth(), baseline5, paint);
    }

    private void drawCenterText(final Canvas canvas) {
        //反面教程
        canvas.save();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextSize(80);
        float width = paint.measureText(mText);
        paint.setTextAlign(Paint.Align.LEFT);
        float left = getWidth() / 2 - width / 2;
        float left_x = left + width * mPercent;

        //计算文字的绘制位置，中线 + 文字的实际高度/2 - 文字的下部高度 = 文字的基准线，绘制在基准线
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float baseline = getHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2;


        Rect rect = new Rect((int) left_x, 0, getWidth(), getHeight());
        canvas.clipRect(rect);
        canvas.drawText(mText, left, baseline, paint);
        canvas.restore();
    }

    private void drawCenterText1(final Canvas canvas) {
        canvas.save();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(80);
        float width = paint.measureText(mText);
        paint.setTextAlign(Paint.Align.LEFT);
        float left = getWidth() / 2 - width / 2;
        float right = left + width * mPercent;
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float baseline = getHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2;
        Rect rect = new Rect((int) left, 0, (int) right, getHeight());
        canvas.clipRect(rect);
        canvas.drawText(mText, left, baseline, paint);
        canvas.restore();

    }

    private void drawCenterLineX(final Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), paint);
    }

    private void drawCenterLineY(final Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
    }


}
