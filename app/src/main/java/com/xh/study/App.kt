package com.xh.study

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
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

        registerActivityLifecycleCallbacks(object :ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity) {
                LogUtils.e("onActivityPaused--${activity}")
            }

            override fun onActivityStarted(activity: Activity) {
                LogUtils.e("onActivityStarted--${activity}")

            }

            override fun onActivityDestroyed(activity: Activity) {
                LogUtils.e("onActivityDestroyed--${activity}")

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                LogUtils.e("onActivitySaveInstanceState--${activity}")

            }

            override fun onActivityStopped(activity: Activity) {
                LogUtils.e("onActivityStopped--${activity}")

            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                LogUtils.e("onActivityCreated--${activity}")

            }

            override fun onActivityResumed(activity: Activity) {
                LogUtils.e("onActivityResumed--${activity}")

            }

        })
    }
}