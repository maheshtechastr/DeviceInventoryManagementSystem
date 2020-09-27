package com.mpg.nagarro.deviceinventorymgmt.di

import com.mpg.nagarro.deviceinventorymgmt.ui.devices.AddEditDeviceFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    /*
    * We define the name of the Fragment we are going
    * to inject the ViewModelFactory into. i.e. in our case
    * The name of the fragment: AddEditDeviceFragment
    */
    @ContributesAndroidInjector
    abstract fun contributeAddEditDeviceFragment(): AddEditDeviceFragment

}