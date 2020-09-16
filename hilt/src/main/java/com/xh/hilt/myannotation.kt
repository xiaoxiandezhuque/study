package com.xh.hilt

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MyInject1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MyInject2