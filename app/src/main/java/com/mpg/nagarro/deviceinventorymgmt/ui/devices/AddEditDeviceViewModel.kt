package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.util.Event
import com.mpg.nagarro.deviceinventorymgmt.util.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditDeviceViewModel
@Inject constructor(private val repository: Repository) : BaseViewModel() {

    var deviceName = MutableLiveData<String>()
    var totalInventory = MutableLiveData<String>()

    private val _taskUpdatedEvent = MutableLiveData<Event<Unit>>()
    val taskUpdatedEvent: LiveData<Event<Unit>> = _taskUpdatedEvent

//    private val _snackbarText = MutableLiveData<String>()
//    val snackbarText: LiveData<String> = _snackbarText

    private val TAG = "AddEditDeviceViewModel"

    fun addDevice() {
        val name = deviceName.value
        val inventory = totalInventory.value
        Log.d(TAG, "addDevice() name = $name called inventory = $inventory")

        if (Utils.isNullOrEmpty(name)) {
            showMessage.value = "Filled can't be empty"
            return
        }

        val totalInventory = getDeviceCount(inventory)

        val deviceEntity = DeviceEntity(name!!, totalInventory, totalInventory)
        addDevice(deviceEntity)
    }

    private fun addDevice(deviceEntity: DeviceEntity) {
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

}