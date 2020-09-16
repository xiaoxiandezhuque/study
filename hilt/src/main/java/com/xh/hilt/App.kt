package com.xh.hilt

import android.app.Application
import com.blankj.utilcode.util.Utils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application(){

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)

    }
}