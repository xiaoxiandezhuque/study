package com.xh.study.activity


import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.xh.study.R
import kotlinx.android.synthetic.main.activity_coordinator.*

class CoordinatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator)
//        val mediaController = MediaController(this)
        video_view.setVideoURI(Uri.parse("rtsp://192.168.42.11/live"))
//        video_view.setMediaController(mediaController)
        video_view.requestFocus()
        video_view.start()

        btn_exit.setOnClickListener {
            video_view.stopPlayback()
        }
    }
}