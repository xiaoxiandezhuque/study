package com.xh.databinding;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;

public class TextBindingAdapter {
    @BindingAdapter({"mytext"})
    public static void mytext(TextView view, String str) {
        view.setText("自定义的：" + str);
    }
    @BindingConversion
    public static String conversionString(String text) {
        return text + "-conversionString";
    }
}
