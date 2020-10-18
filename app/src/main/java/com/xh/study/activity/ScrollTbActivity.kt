package com.xh.study.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xh.study.R
import com.xh.study.activity.scrolltb.ScrollTbFragment
import com.xh.study.base.MyFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_scroll_tb.*

class ScrollTbActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_tb)

        val names = arrayOf("banner111号", "banner222号", "banner333号", "banner444号")
        val fragments = arrayOf(
            ScrollTbFragment.create(111), ScrollTbFragment.create(222),
            ScrollTbFragment.create(333), ScrollTbFragment.create(444)
        )
        stl_viewpager.adapter = MyFragmentPagerAdapter(
            this,
            fragments,
        )
        slide_tab_layout.setViewPager(
            stl_viewpager,
            names
        )
    }
}