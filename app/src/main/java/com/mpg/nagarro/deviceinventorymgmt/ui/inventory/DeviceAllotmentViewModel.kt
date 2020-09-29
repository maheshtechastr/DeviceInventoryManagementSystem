package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceStatus
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import com.mpg.nagarro.deviceinventorymgmt.util.Utils
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class DeviceAllotmentViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    private val devices: LiveData<List<DeviceEntity>> = repository.observeAvailableDevices()
    val employees: LiveData<List<EmployeeEntity>> = repository.getEmployeeList()

    val employeeNames: LiveData<List<String>> = Transformations.map(employees) {
        it.map { employeeEntity ->
            employeeEntity.name
        }
    }
    val deviceNames: LiveData<List<String>> = Transformations.map(devices) {
        it.map { deviceEntity ->
            deviceEntity.name
        }
    }

    fun saveData(deviceEntity: DeviceEntity, employeeEntity: EmployeeEntity, returnedDate: String) {

        val c: Calendar = Calendar.getInstance()
        val currentDate = Date()
        println("currentDate = $currentDate")

        val rdList = returnedDate.split("/")
        if (rdList.size < 3)
            return
        c.set(rdList[2].toInt(), rdList[1].toInt(), rdList[0].toInt())
        println("updatedDate = ${c.time}")

        val status = Utils.enumToIntDeviceStatus(DeviceStatus.ISSUED)

        isLoading.value = true

        viewModelScope.launch {
            repository.addDeviceInventory(
                DeviceInventory(
                    status,
                    currentDate,
                    c.time,
                    employeeEntity.empId,
                    deviceEntity.deviceId,
                    employeeEntity.name,
                    deviceEntity.name,
                )
            )
            /*Updated current Available inventory into DeviceEntity*/
            repository.updateAvailableInventory(
                deviceEntity.currentAvailableInventory - 1,
                deviceEntity.deviceId
            )
            isLoading.value = false
        }
    }

    fun getEmployeeEntity(employeeName: String): EmployeeEntity? {
        val empIndex = employeeNames.value?.indexOf(employeeName)
        return empIndex?.let {
            if (it == -1)
                null
            else
                employees.value?.get(it)
        }
    }

    fun getDeviceEntity(deviceName: String): DeviceEntity? {
        val devIndex = deviceNames.value?.indexOfFirst { it.equals(deviceName) }
        return devIndex?.let {
            if (it == -1)
                null
            else
                devices.value?.get(it)
        }
    }

}