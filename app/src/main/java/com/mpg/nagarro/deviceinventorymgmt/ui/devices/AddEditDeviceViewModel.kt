package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.common.SingleLiveEvent
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.Result
import com.mpg.nagarro.deviceinventorymgmt.util.Event
import com.mpg.nagarro.deviceinventorymgmt.util.Utils
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AddEditDeviceViewModel
@Inject constructor(private val repository: Repository) : BaseViewModel() {

    var deviceName = SingleLiveEvent<String>()
    var totalInventory = SingleLiveEvent<String>()

    private val _taskUpdatedEvent = SingleLiveEvent<Event<Unit>>()
    val taskUpdatedEvent: LiveData<Event<Unit>> = _taskUpdatedEvent

    fun addDevice() {
        val name = deviceName.value
        val inventory = totalInventory.value
        Timber.d("addDevice() name = $name called inventory = $inventory")

        if (Utils.isNullOrEmpty(name)) {
            showMessage.value = "Filled can't be empty"
            return
        }

        val totalInventoryI = getDeviceCount(inventory)
        totalInventory.value = totalInventoryI.toString()
        val deviceEntity = DeviceEntity(name!!, totalInventoryI, totalInventoryI)

        viewModelScope.launch {
            repository.addDevice(deviceEntity)
            showMessage.value = "Device saved!"
            _taskUpdatedEvent.postValue(Event(Unit))
        }
    }


    private fun getDeviceCount(totalC: String?): Int {
        return if (totalC == null || totalC.isEmpty())
            1
        else
            try {
                totalC.toInt()
            } catch (e: NumberFormatException) {
                1
            }
    }

    private var isNewTask: Boolean = false

    private var isDataLoaded = false

    fun start(deviceId: Int?) {
        if (isLoading.value == true) {
            return
        }

        if (deviceId == null) {
            // No need to populate, it's a new task
            isNewTask = true
            return
        }
        if (isDataLoaded) {
            // No need to populate, already have data.
            return
        }

        isNewTask = false
        isLoading.value = true

        viewModelScope.launch {
            repository.getDeviceRById(deviceId).let { result ->
                if (result is Result.Success) {
                    onTaskLoaded(result.data)
                } else {
                    onDataNotAvailable()
                }
            }
        }
    }

    private fun onTaskLoaded(device: DeviceEntity) {
        deviceName.value = device.name
        totalInventory.value = device.totalInventory.toString()
        isLoading.value = false
        isDataLoaded = true
    }

    private fun onDataNotAvailable() {
        isLoading.value = false
    }

}