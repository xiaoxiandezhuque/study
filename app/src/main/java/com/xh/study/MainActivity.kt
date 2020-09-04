package com.xh.study

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.xh.butterknife_annotation.BindView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sample_text.text = stringFromJNI()
        Text().test(this)
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
