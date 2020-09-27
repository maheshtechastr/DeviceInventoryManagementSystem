package com.mpg.nagarro.deviceinventorymgmt

import android.app.Application
import android.util.Log
import com.mpg.nagarro.deviceinventorymgmt.di.AppComponent
import com.mpg.nagarro.deviceinventorymgmt.di.DaggerAppComponent

class MyApplication : Application() {
    private val TAG = "MyApplication"
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
        Log.d(TAG, "null() called");
    }

    private fun initDagger(application: Application): AppComponent {
        return DaggerAppComponent
            .factory()
            .create(application.applicationContext)
    }
}