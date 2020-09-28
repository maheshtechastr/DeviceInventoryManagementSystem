package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import android.util.Log
import androidx.lifecycle.LiveData
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import javax.inject.Inject

class DeviceListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {

    private val TAG = "DeviceListViewModel"

    val devices: LiveData<List<DeviceEntity>> = repository.getDeviceList()

    fun deleteRow(item: DeviceEntity) {
        val position = devices.value?.indexOf(item)
        Log.i(TAG, "$position Row : ${item.name}");
    }

}