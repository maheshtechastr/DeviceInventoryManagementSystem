package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import androidx.lifecycle.LiveData
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import javax.inject.Inject

class DeviceAllottedListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {

    val deviceInventoryList: LiveData<List<DeviceInventory>> = repository.getDeviceInventoryList()
}