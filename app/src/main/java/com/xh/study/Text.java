package com.xh.study;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

import com.xh.butterknife_annotation.BindView;

public class Text {

    @BindView(1)
    public void test(Activity activity) {
        GeneratedClass generatedClass = new GeneratedClass();
        String message = generatedClass.getMessage();
        // android.support.v7.app.AlertDialog
        new AlertDialog.Builder(activity)
                .setPositiveButton("Ok", null)
                .setTitle("Annotation Processor Messages")
                .setMessage(message)
                .show();
    }

}
