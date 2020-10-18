package com.xh.study

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        Utils.init(this)


        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.refresh_bg, R.color.refresh_text);//全局设置主题颜色
            return@setDefaultRefreshHeaderCreator ClassicsHeader(context)
        }
    }
}