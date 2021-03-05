package com.xh.study

import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.xh.study.activity.*
import com.xh.study.aidl.AidlService
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
        list.add("Coordinator自定义Behavior")
        list.add("自定义viewgroup")
        list.add("有轻重的签名画笔")
        list.add("livedata")
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
                4 -> {
                    startActivity(Intent(this, CoordinatorActivity::class.java))
                }
                5 -> {
                    startActivity(Intent(this, ViewGroupActivity::class.java))
                }
                6 -> {
                    startActivity(Intent(this, SignActivity::class.java))
                }

                7 -> {
                    startActivity(Intent(this, SignActivity::class.java))
                }
            }
        }



//        val iterator = ServiceLoader.load(IAuto::class.java).iterator()
//        while (iterator.hasNext()) {
//            LogUtils.e(iterator.next().getName())
//        }

//        bindService(Intent(this, AidlService::class.java),object : ServiceConnection {
//            override fun onServiceDisconnected(name: ComponentName?) {
//
//            }
//
//            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//                IMyAidlInterface.Stub.asInterface(service)
//            }
//
//        }, Context.BIND_AUTO_CREATE)



    }


    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}
