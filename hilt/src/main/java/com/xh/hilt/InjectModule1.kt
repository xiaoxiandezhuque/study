package com.xh.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Singleton


@Module
/**
 * ApplicationComponent	Application
 *ActivityRetainedComponent	ViewModel
 *ActivityComponent	Activity
 *FragmentComponent	Fragment
 *ViewComponent	View
 *ViewWithFragmentComponent	带有 @WithFragmentBindings 注释的 View
 *ServiceComponent	Service
 * 确定注入的范围
 */
@InstallIn(ActivityComponent::class, FragmentComponent::class)
abstract class InjectModule1 {

    //接口的注入，传入具体实现，返回接口类型
    //使用一个自定义注解来区别不同的实现
    @Binds
    @MyInject1
    abstract fun bingInject2(myInterfaceImpl: MyInterfaceImpl): MyInterface

    @Binds
    @MyInject2
    abstract fun bingInject1(myInterfaceImplTwo: MyInterfaceImplTwo): MyInterface
}