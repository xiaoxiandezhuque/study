package com.xh.roomtest

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        initLookDB()
        app = this
    }

    companion object{
        lateinit var app:App
    }

    //初始化看数据库的框架
    //网页打开  chrome://inspect/#devices  第一次需要翻墙
    private fun initLookDB() {
        Stetho.initializeWithDefaults(this)
//        OkHttpClient.Builder()
//            .addNetworkInterceptor(StethoInterceptor())
//            .build()
    }
}