package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import android.util.Log
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

    fun updateDeviceStatus(deviceInventory: DeviceInventory) {
        Log.i(TAG, "updateDeviceStatus: status = ${deviceInventory.status}")
        viewModelScope.launch {
            repository.updateInventoryStatus(deviceInventory.recordId, deviceInventory.status)
        }
    }

    fun isReturned(status: Int): Boolean {
        return Utils.intDeviceStatusToEnum(status) == DeviceStatus.RETURNED
    }

    fun isLost(status: Int): Boolean {
        return Utils.intDeviceStatusToEnum(status) == DeviceStatus.RETURNED
    }
}