package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceAllottedListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    private val TAG = "DeviceAllottedListViewM"
    val deviceInventoryList: LiveData<List<DeviceInventory>> = repository.getDeviceInventoryList()

    fun deleteRow(deviceInventory: DeviceInventory) = viewModelScope.launch {
        Log.d(TAG, "deleteRow() called = ${deviceInventory.devName}")
       val result =  repository.deleteDeviceInventory(deviceInventory.empId)
        Log.i(TAG, "deleteRow: result = $result");
    }
}