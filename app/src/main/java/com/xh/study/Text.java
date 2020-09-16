package com.xh.study;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

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


    }

}
