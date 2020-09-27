package com.mpg.nagarro.deviceinventorymgmt.factory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.AddEditDeviceViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.DeviceListViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.AddEditEmployeeViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.EmployeeListViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.DeviceAllotmentViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.DeviceAllottedListViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: Repository,
    ) :
    ViewModelProvider.Factory {
    private val TAG = "ViewModelFactory"
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Log.i(TAG, "create: =====>");
        return with(modelClass) {
            when {
                isAssignableFrom(AddEditDeviceViewModel::class.java) ->
                    AddEditDeviceViewModel(repository)
                isAssignableFrom(AddEditEmployeeViewModel::class.java) ->
                    AddEditEmployeeViewModel(repository)
                 isAssignableFrom(DeviceListViewModel::class.java) ->
                     DeviceListViewModel(repository)
                 isAssignableFrom(EmployeeListViewModel::class.java) ->
                     EmployeeListViewModel(repository)
                 isAssignableFrom(DeviceAllotmentViewModel::class.java) ->
                     DeviceAllotmentViewModel(repository)
                 isAssignableFrom(DeviceAllottedListViewModel::class.java) ->
                     DeviceAllottedListViewModel(repository)
                else ->
                    error("Invalid View Model class")
            }

        } as T
    }
}