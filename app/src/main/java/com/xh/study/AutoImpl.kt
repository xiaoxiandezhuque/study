package com.xh.study

import com.google.auto.service.AutoService

@AutoService(IAuto::class)
class AutoImpl : IAuto {
    override fun getName(): String {
        return "iauto"
    }

}