package com.xh.douyinvideoplayer;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class TikTokActivity extends AppCompatActivity {


    private ViewPager2 viewPager2;
    private TikTokRecyclerViewAdapter mAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_tiktok);

        viewPager2 = findViewById(R.id.view_pager);

        mAdapter = new TikTokRecyclerViewAdapter(this);
        viewPager2.setAdapter(mAdapter);
//        viewPager2.removeOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
//            @Override
//            public void onViewAttachedToWindow(View v) {
//
//            }
//
//            @Override
//            public void onViewDetachedFromWindow(View v) {
////                        v.getLayoutParams()
//            }
//        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                LogUtils.e("onPageScrolled--"+position);
            }
 
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);


                mAdapter.play(position);
//                mAdapter.notifyDataSetChanged();
//                LogUtils.e("onPageSelected--"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
}
