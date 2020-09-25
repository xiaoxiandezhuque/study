package com.xh.study

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xh.study.activity.FishActivity
import com.xh.study.activity.LineViewActivity
import com.xh.study.activity.ScrollTbActivity
import com.xh.study.activity.TextActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        TextAdapter(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        recycler_view.adapter = mAdapter

        val list = mutableListOf<String>()
        list.add("音乐播放控件")
        list.add("淘宝首页滑动控件")
        list.add("文字绘制控件")
        list.add("滑动的鱼控件")
        mAdapter.resetData(list)

        mAdapter.setOnItemClickListener { view, postion, data ->
            when (postion) {
                0 -> {
                    startActivity(Intent(this, LineViewActivity::class.java))
                }
                1 -> {
                    startActivity(Intent(this, ScrollTbActivity::class.java))
                }
                2 -> {
                    startActivity(Intent(this, TextActivity::class.java))
                }
                3 -> {
                    startActivity(Intent(this, FishActivity::class.java))
                }
            }
        }
        startActivity(Intent(this, LineViewActivity::class.java))
    }


    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}
