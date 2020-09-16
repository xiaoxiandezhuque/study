
package com.xh.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    @ApplicationContext 和 @ActivityContext    可以直接注入context



    //直接使用inject的注入
    @Inject
    lateinit var inject1: Inject1

    //注入接口，在InjectModule定义接口的具体实现
    @Inject
    @MyInject2
    lateinit var myInterface: MyInterface

    @Inject
    lateinit var inject2: Inject2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inject1.log()
        myInterface.log()
        inject2.log()
    }
}
