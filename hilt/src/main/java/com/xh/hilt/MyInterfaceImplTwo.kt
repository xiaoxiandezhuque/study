package com.xh.hilt

import com.blankj.utilcode.util.LogUtils
import javax.inject.Inject

class MyInterfaceImplTwo @Inject constructor(): MyInterface {
    override fun log() {
        LogUtils.e("---MyInterfaceImplTwo----")
    }

}