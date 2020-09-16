package com.xh.hilt

import com.blankj.utilcode.util.LogUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyInterfaceImpl @Inject constructor(): MyInterface {
    override fun log() {
        LogUtils.e("---MyInterfaceImpl----")
    }

}