package com.flyco.tablayout;

import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.util.LogUtils;

public class TabOnPageChangeCallBack extends ViewPager2.OnPageChangeCallback {

    private SlidingTabLayout mSlidingTabLayout;

    public TabOnPageChangeCallBack(SlidingTabLayout slidingTabLayout) {
        mSlidingTabLayout = slidingTabLayout;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        mSlidingTabLayout.onPageScrolled(position, positionOffset, positionOffsetPixels);

    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        mSlidingTabLayout.onPageSelected(position);
        LogUtils.e("onPageSelected   "+position);
    }
}
