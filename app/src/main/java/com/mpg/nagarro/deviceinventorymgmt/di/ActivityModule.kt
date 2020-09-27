package com.mpg.nagarro.deviceinventorymgmt.di

import com.mpg.nagarro.deviceinventorymgmt.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    /*
     * We modify our ActivityModule by adding the
     * FragmentModule to the Activity which contains
     * the fragment
     */
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}