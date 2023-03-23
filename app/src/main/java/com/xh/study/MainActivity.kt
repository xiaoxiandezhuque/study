package com.xh.study

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.FileIOUtils
import com.xh.study.activity.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

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
        list.add("Coordinator自定义Behavior")
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
                3 -> {
                    startActivity(Intent(this, CoordinatorActivity::class.java))
                }
            }
        }
        startActivity(Intent(this, CoordinatorActivity::class.java))


    }


    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}
