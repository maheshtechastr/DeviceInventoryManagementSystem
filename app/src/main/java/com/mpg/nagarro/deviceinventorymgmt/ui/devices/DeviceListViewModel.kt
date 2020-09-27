package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import androidx.lifecycle.LiveData
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import javax.inject.Inject

class DeviceListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {

    val devices: LiveData<List<DeviceEntity>> = repository.getDeviceList()

}