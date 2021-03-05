package com.xh.study.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xh.study.R
import kotlinx.android.synthetic.main.activity_coordinator.*

class CoordinatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator)

        coordinator.viewTreeObserver.removeOnPreDrawListener {

            return@removeOnPreDrawListener true
        }
    }
}