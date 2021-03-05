package com.xh.study.activity

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.xh.study.R
import kotlinx.android.synthetic.main.activity_line_view.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.thread

class LineViewActivity : AppCompatActivity() {
    val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_view)

        val list = mutableListOf<Int>()

        for (i in 0 until 1000) {
            list.add(random.nextInt(100))
        }

        var count = 0
        line_view.setDecibel(list, mutableListOf(3000, 5000, 10000));
//        line_view.setPlayCount(count)
        btn.setOnClickListener {
            line_view.setPlaying(!line_view.getPlaying())
            if (line_view.playing) {
                time = 0
                setData()
            } else {
                handler.removeCallbacksAndMessages(null)
            }
        }
        btn1.setOnClickListener {
            line_view.setSelect(true)
        }

        btn2.setOnClickListener {
            line_view.setPlayCount(++count)
        }
        line_view.setOnSelectListener { startTime, endTime ->
            LogUtils.e(startTime, endTime)
        }

        line_view.post {
            line_view.setPlayCount(0)
        }
        line_view.setFillTheScreen(true)

//        showAnnotationMessage()

//        setData()
    }

    private val handler = Handler()
    private var time = 0
    private fun setData() {
        if (time > 10000) {
            return
        }
        time = time + 20
        line_view.setPlayTime(time)
        handler.postDelayed({
            setData()
        }, 20)
    }


}
