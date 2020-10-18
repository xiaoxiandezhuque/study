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
        line_view.setPlayCount(count)
        btn.setOnClickListener {
            line_view.setPlayCount(++count)
        }
        line_view.setOnSelectListener { startTime, endTime ->
            LogUtils.e(startTime, endTime)
        }
//        showAnnotationMessage()

//        setData()
    }

//    private val handler = Handler()
//    private fun setData() {
//        val fhrList = mutableListOf<Int>()
//        val tocoList = mutableListOf<Int>()
//        for (i in 0 until 120) {
//            fhrList.add(60)
//            tocoList.add(10)
//        }
//
//        fhr_view.setData(fhrList, tocoList)
//        handler.postDelayed({
//            setData()
//        }, 1000)
//    }


//    private fun showAnnotationMessage() {
//        val generatedClass = Class.forName("").
//
//        val message: String = generatedClass.getMessage()
//
//        AlertDialog.Builder(this)
//            .setPositiveButton("Ok", null)
//            .setTitle("Annotation Processor Messages")
//            .setMessage(message)
//            .show()
//    }


    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}
