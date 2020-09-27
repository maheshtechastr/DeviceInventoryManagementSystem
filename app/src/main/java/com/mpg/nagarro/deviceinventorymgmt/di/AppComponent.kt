package com.mpg.nagarro.deviceinventorymgmt.di

import android.content.Context
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.AddEditDeviceFragment
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.DeviceListFragment
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.AddEditEmployeeFragment
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.EmployeeListFragment
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.DeviceAllotmentFragment
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.DeviceAllottedListFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [ApplicationModule::class,
    ActivityModule::class,
    AndroidSupportInjectionModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

//    fun inject(fragment: AddEditDeviceFragment)
//    fun inject(fragment: AddEditEmployeeFragment)
//    fun inject(fragment: EmployeeListFragment)
//    fun inject(fragment: DeviceListFragment)
//    fun inject(fragment: DeviceAllottedListFragment)
//    fun inject(fragment: DeviceAllotmentFragment)
}