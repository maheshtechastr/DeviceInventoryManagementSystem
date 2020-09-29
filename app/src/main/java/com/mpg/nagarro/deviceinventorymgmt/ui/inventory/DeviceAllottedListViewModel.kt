package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceStatus
import com.mpg.nagarro.deviceinventorymgmt.util.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceAllottedListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    private val TAG = "DeviceAllottedListViewM"

    val deviceInventoryList: LiveData<List<DeviceInventory>> = repository.getDeviceInventoryList()

    fun updateDeviceStatus(deviceInventory: DeviceInventory, deviceStatus: DeviceStatus) {
        var result: Int
        viewModelScope.launch {
            val deviceEntity = repository.getDeviceById(deviceInventory.deviceId)
            deviceEntity?.let {
                when (deviceStatus) {
                    DeviceStatus.RETURNED -> {
                        result = repository.updateInventoryStatus(
                            deviceInventory.recordId,
                            Utils.enumToIntDeviceStatus(DeviceStatus.RETURNED)
                        )
                        if (result > 0)
                            repository.updateAvailableInventory(
                                deviceEntity.currentAvailableInventory + 1,
                                deviceEntity.deviceId
                            )
                    }
                    DeviceStatus.LOST -> {
                        result = repository.updateInventoryStatus(
                            deviceInventory.recordId,
                            Utils.enumToIntDeviceStatus(DeviceStatus.LOST)
                        )
                        if (result > 0)
                            repository.updateTotalInventory(
                                deviceEntity.totalInventory - 1,
                                deviceEntity.deviceId
                            )
                    }
                    else -> {
                        result = -1
                    }
                }
            }


        }
    }

}