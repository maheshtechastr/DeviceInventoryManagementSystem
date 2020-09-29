package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {

    val devices: LiveData<List<DeviceEntity>> = repository.getDeviceList()

    fun deleteRowAction(item: DeviceEntity) = viewModelScope.launch {

        val deviceEntity = repository.getDeviceById(item.deviceId)
        deviceEntity?.let {
            if (it.totalInventory == it.currentAvailableInventory)
                repository.deleteDevice(item.deviceId)
            else
                showMessage.postValue("\" ${item.name} \" can't be deleted \n  Inventory either not returned or lost. ")
        }

    }

}