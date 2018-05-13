package com.takeiteasy.vip.uklontest.di.modules

import com.takeiteasy.vip.uklontest.comments.PostCommentsActivity
import com.takeiteasy.vip.uklontest.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector
    abstract fun bindPostCommentsActivity(): PostCommentsActivity
}