package com.xh.study

import java.io.BufferedInputStream
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel


fun main11() {
//    val buff = BufferedInputStream(FileInputStream(""))
//    DataStreamTest.testDataOutPutStream()
//    DataStreamTest.testDataInputStreamI()
    val byteBuffer = ByteBuffer.allocate(100)
    byteBuffer.compact()
    byteBuffer.put((22).toByte())
    byteBuffer.put((23).toByte())
    byteBuffer.put((24).toByte())
    byteBuffer.put((25).toByte())
//    byteBuffer.put((26).toByte())
    println(byteBuffer.position())
    println("------------")
    byteBuffer.flip()
    println(byteBuffer.get())
    byteBuffer.compact()

    byteBuffer.put((26).toByte())
    byteBuffer.put((27).toByte())
    byteBuffer.put((28).toByte())
    byteBuffer.flip()
    println("当前数据的位置" + byteBuffer.position())
    println("当前数据的大小" + byteBuffer.limit())
    println(byteBuffer.get())//取出当前的第一个数据

    println("当前数据的位置" + byteBuffer.position())
    println("当前数据的大小" + byteBuffer.limit())


//
//    println(byteBuffer.limit())
//    byteBuffer.clear()
//    byteBuffer.put((25).toByte())
//    byteBuffer.put((26).toByte())
//    byteBuffer.flip()
//    println(byteBuffer.get())
//    println(byteBuffer.position())
//    println(byteBuffer.limit())
//    println(byteBuffer.remaining())
//    byteBuffer.compact()
//    byteBuffer.put((25).toByte())
//    byteBuffer.flip()
//    println(byteBuffer.limit())
//    byteBuffer.clear()
//    byteBuffer.put((27).toByte())
//    println(byteBuffer.get(0))

}

fun main2() {
    println(Math.toRadians(45.0))
    println(Math.sin(360.0))
    println(Math.sin(390.0))
    println(Math.sin(Math.toRadians(360.0)))
    println(Math.sin(Math.toRadians(390.0)))
}


fun main() {

    println(215036 * 215036)

}