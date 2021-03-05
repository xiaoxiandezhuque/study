package com.xh.study

import com.blankj.utilcode.util.EncryptUtils
import java.nio.ByteBuffer
import java.util.concurrent.Callable
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.FutureTask
import java.util.concurrent.locks.LockSupport


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
    val a = "aaa"
    val test = Text()


//    val map = HashMap<String, String>()
//    map.put("a", "b")
//    val set = HashSet<String>()
//    set.add("a")
//    set.contains()

//     ThreadPoolExecutor()
//    val futureTask = FutureTask<String>(object : Callable<String> {
//        override fun call(): String {
//            LockSupport.park()
//            return "aaa"
//        }
//
//    })
//    val thread = Thread(futureTask)
//    thread.start()
////    thread.join()
////    val lock = ReentrantLock()
////    lock.lock()
////    val condition =lock.newCondition();
////    condition.await()
////    println(futureTask.get())
////    println(futureTask.get(100L,TimeUnit.MILLISECONDS))
//    Thread.sleep(1000)
////    thread.interrupt()
//    LockSupport.unpark(thread)
//    println(thread.isInterrupted)
//    println(thread.isInterrupted)
//
//    val map =
//        ConcurrentHashMap<String, String>()
//    map["a"] = "a"


    println(Text.tsestTry())
}

fun main4() {
    val key = "4OYy/Obh0V6/dpXa"

    val jiami = AESCryptoSecurity.encrypt(
        "{\"msgContent\":{\"giftIcon\":\"https://meiban-1255871614.cos.ap-guangzhou.myqcloud.com/gift/webp/%E6%91%A9%E6%89%98%E8%BD%A6.webp\",\"giftId\":11,\"giftJsonUrl\":\"https://meiban-1255871614.cos.ap-guangzhou.myqcloud.com/gift/json/%E6%91%A9%E6%89%98%E8%BD%A6.json\",\"giftName\":\"摩托车\",\"giftPrice\":36888,\"giftSort\":11,\"giftSvgaUrl\":\"https://meiban-1255871614.cos.ap-guangzhou.myqcloud.com/gift/svga/%E6%91%A9%E6%89%98%E8%BD%A6.svga\",\"giftType\":0},\"msgType\":101,\"receiveUserId\":\"10111134\",\"sendUserId\":\"10111132\"}",
        key
    )
    println(jiami)
    println(AESCryptoSecurity.decrypt(jiami, key))

}