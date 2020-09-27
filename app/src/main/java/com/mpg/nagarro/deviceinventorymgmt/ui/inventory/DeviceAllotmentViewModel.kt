package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import javax.inject.Inject

class DeviceAllotmentViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    private val devices: LiveData<List<DeviceEntity>> = repository.getDeviceList()
    private val employees: LiveData<List<EmployeeEntity>> = repository.getEmployeeList()
    val employeeName: LiveData<List<String>> = Transformations.map(employees) {
        it.map { employeeEntity ->
            employeeEntity.name
        }
    }
    val deviceName: LiveData<List<String>> = Transformations.map(devices) {
        it.map { deviceEntity ->
            deviceEntity.name
        }
    }

}