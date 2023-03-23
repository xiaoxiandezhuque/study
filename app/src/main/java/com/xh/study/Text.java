package com.xh.study;

import android.os.Looper;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Text {

//    @BindView(1)
//    public void test(Activity activity) {
//        GeneratedClass generatedClass = new GeneratedClass();
//        String message = generatedClass.getMessage();
//        // android.support.v7.app.AlertDialog
//        new AlertDialog.Builder(activity)
//                .setPositiveButton("Ok", null)
//                .setTitle("Annotation Processor Messages")
//                .setMessage(message)
//                .show();
//    }

    public static void main(String[] args) {
        System.out.println("aaa");

        Thread thread;

    }

    public void test(String ss) throws Exception {
        byte a = (byte) 0xff;
        ByteBuffer byteBuffer = ByteBuffer.allocate(111);
        byteBuffer.put((byte) -1);
        byteBuffer.get();


        FileInputStream is = new FileInputStream("");
        BufferedInputStream buf = new BufferedInputStream(is);
        buf.read();
        is.read();


        LockSupport.park();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.get();

//        Thread.sleep();
        new Object().wait(1);
        Looper.prepare();
        Looper.loop();
//        Looper.myLooper()

        Object obj = new Object();
        ReentrantLock lock = new ReentrantLock();
        lock.tryLock();
        lock.unlock();
        Condition condition = lock.newCondition();
        condition.await();
    }

    public static int tsestTry() {
        try {
            int a = 1 / 1;
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {

        }

    }

}
