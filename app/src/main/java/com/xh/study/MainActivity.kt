package com.xh.study

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sample_text.text = stringFromJNI()
        thread {
            while (true) {
                LogUtils.e("开始睡觉",Thread.currentThread().name)
                Thread.sleep(50000)
                LogUtils.e("结束睡觉")
            }
        }

//        showAnnotationMessage()
    }

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
