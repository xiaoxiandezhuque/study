package com.xh.study.proxy

import java.lang.reflect.Proxy

fun main() {



    val proxy = Proxy.newProxyInstance(
        ClassLoader.getSystemClassLoader(),
        arrayOf(IStudent::class.java),
        StudentProxy(object :IStudent{
            override fun study() {
                println("学习")
            }

        })
    ) as IStudent

    proxy.study()

}