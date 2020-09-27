package com.mpg.nagarro.deviceinventorymgmt.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpg.nagarro.deviceinventorymgmt.di.scope.ViewModelKey
import com.mpg.nagarro.deviceinventorymgmt.factory.ViewModelFactory
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.AddEditDeviceViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.DeviceListViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.AddEditEmployeeViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.EmployeeListViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.DeviceAllotmentViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.DeviceAllottedListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  AddEditDeviceViewModel.class as key,
     * and a Provider that will build a AddEditDeviceViewModel
     * object.
     *
     * */
    @Binds
    @IntoMap
    @ViewModelKey(AddEditDeviceViewModel::class)
    protected abstract fun addEditDeviceViewModel(addEditDeviceViewModel: AddEditDeviceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeviceListViewModel::class)
    protected abstract fun deviceListViewModel(deviceListViewModel: DeviceListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddEditEmployeeViewModel::class)
    protected abstract fun addEditEmployeeViewModel(addEditEmployeeViewModel: AddEditEmployeeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeListViewModel::class)
    protected abstract fun employeeListViewModel(employeeListViewModel: EmployeeListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeviceAllotmentViewModel::class)
    protected abstract fun deviceAllotmentViewModel(deviceAllotmentViewModel: DeviceAllotmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeviceAllottedListViewModel::class)
    protected abstract fun deviceAllottedListViewModel(deviceAllottedListViewModel: DeviceAllottedListViewModel): ViewModel

}
