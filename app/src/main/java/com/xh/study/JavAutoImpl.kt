package com.xh.study

import com.google.auto.service.AutoService
import com.xh.study.IAuto

@AutoService(IAuto::class)
class JavAutoImpl : IAuto {
    override fun getName(): String {
        return "JavAutoImpl"
    }
}