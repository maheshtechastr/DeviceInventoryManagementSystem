package com.mpg.nagarro.deviceinventorymgmt.di

import com.mpg.nagarro.deviceinventorymgmt.ui.devices.AddEditDeviceFragment
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.DeviceListFragment
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.AddEditEmployeeFragment
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.EmployeeListFragment
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.DeviceAllotmentFragment
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.DeviceAllottedListFragment
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

    @ContributesAndroidInjector
    abstract fun contributeDeviceListFragment(): DeviceListFragment

    @ContributesAndroidInjector
    abstract fun contributeAddEditEmployeeFragment(): AddEditEmployeeFragment

    @ContributesAndroidInjector
    abstract fun contributeEmployeeListFragment(): EmployeeListFragment

    @ContributesAndroidInjector
    abstract fun contributeDeviceAllotmentFragment(): DeviceAllotmentFragment

    @ContributesAndroidInjector
    abstract fun contributeDeviceAllottedListFragment(): DeviceAllottedListFragment
}