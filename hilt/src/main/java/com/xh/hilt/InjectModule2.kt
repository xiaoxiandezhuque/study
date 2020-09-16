package com.xh.hilt

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
object InjectModule2 {

    @Provides
    fun inject2(): Inject2 {
        return Inject2()
    }
}