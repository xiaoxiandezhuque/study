package com.xh.hilt

import com.blankj.utilcode.util.LogUtils
import javax.inject.Inject


class Inject1 @Inject constructor() {


    fun log(){
        LogUtils.e("--injetc111111--")
    }
}