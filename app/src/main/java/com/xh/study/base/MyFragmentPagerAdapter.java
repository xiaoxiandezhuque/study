package com.xh.study.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Created by BHKJ on 2016/8/19.
 */

public class MyFragmentPagerAdapter extends FragmentStateAdapter {

    private Fragment[] mFragments;

    public MyFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity, Fragment[] fragments) {
        super(fragmentActivity);
        mFragments = fragments;
    }
//    private String[] mTitles;

//    public MyFragmentPagerAdapter(Fragment[] fragments, FragmentManager fm) {
//        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        mFragments = fragments;
//    }

//    public MyFragmentPagerAdapter(Fragment[] fragments, FragmentManager fm, String[] titles) {
//        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        mFragments = fragments;
//        mTitles = titles;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return mFragments[position];
//    }
//
//    @Override
//    public int getCount() {
//        return mFragments.length;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mTitles != null ? mTitles[position] : "";
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments[position];
    }

    @Override
    public int getItemCount() {
        return mFragments.length;
    }
}
