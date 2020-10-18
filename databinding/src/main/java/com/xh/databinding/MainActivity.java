package com.xh.databinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.xh.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding dataBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserBean user = new UserBean();
        user.setName("小明");
        dataBinding.setBean(user);

//        dataBinding.btnData.setOnClickListener((v) -> {
//            user.setName("aa");
//        });
    }

}
//    private int i = 0;
//   dataBinding.btnData.setOnClickListener {
//           dataBinding.tvContent.setText("aa")
//           student
////            dataBinding.bean = StudentBean("${i++}")
////            student.name="小明$i"
////            dataBinding.executePendingBindings()
//           }


