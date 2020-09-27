package com.mpg.nagarro.deviceinventorymgmt

import android.app.Activity
import android.app.Application
import com.mpg.nagarro.deviceinventorymgmt.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    /** Returns an [AndroidInjector] of [Activity]s.  */
    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return androidInjector
    }

}