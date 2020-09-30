package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceStatus
import com.mpg.nagarro.deviceinventorymgmt.util.Event
import com.mpg.nagarro.deviceinventorymgmt.util.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceAllottedListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    private val TAG = "DeviceAllottedListViewM"

    private val _deviceInventoryList: LiveData<List<DeviceInventory>> =
        repository.getDeviceInventoryList()
    var deviceInventoryList: LiveData<List<DeviceInventory>> = _deviceInventoryList

    private var currentFiltering = DeviceStatus.ISSUED

    private val _openDeviceInventoryEvent = MutableLiveData<Event<String>>()
    val openDeviceInventoryEvent: LiveData<Event<String>> = _openDeviceInventoryEvent

    private val _newDeviceInventoryEvent = MutableLiveData<Event<Unit>>()
    val newDeviceInventoryEvent: LiveData<Event<Unit>> = _newDeviceInventoryEvent

    private val _currentFilteringLabel = MutableLiveData<Int>()
    val currentFilteringLabel: LiveData<Int> = _currentFilteringLabel

    private val _noDeviceInventoriesLabel = MutableLiveData<Int>()
    val noDeviceInventoriesLabel: LiveData<Int> = _noDeviceInventoriesLabel

    private val _noDeviceInventoryIconRes = MutableLiveData<Int>()
    val noDeviceInventoryIconRes: LiveData<Int> = _noDeviceInventoryIconRes

    private val _tasksAddViewVisible = MutableLiveData<Boolean>()
    val tasksAddViewVisible: LiveData<Boolean> = _tasksAddViewVisible

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(_deviceInventoryList) {
        it.isEmpty()
    }

    init {
        setFiltering(currentFiltering)
    }

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

    fun setFiltering(requestType: DeviceStatus) {
        currentFiltering = requestType

        // Depending on the filter type, set the filtering label, icon drawables, etc.
        when (requestType) {
            DeviceStatus.ISSUED -> {
                setFilter(
                    R.string.issued_menu_filter, R.string.no_issued_inventory,
                    R.drawable.logo_no_fill, true
                )
            }
            DeviceStatus.LOST -> {
                setFilter(
                    R.string.lost_menu_filter, R.string.no_lost_inventory,
                    R.drawable.logo_no_fill, true
                )
            }
            DeviceStatus.RETURNED ->{
                setFilter(
                    R.string.returned_menu_filter, R.string.no_returned_inventory,
                    R.drawable.logo_no_fill, true
                )
            }
            DeviceStatus.AVAILABLE -> {
                setFilter(
                    R.string.all_menu_filter, R.string.no_inventory,
                    R.drawable.logo_no_fill, true
                )
            }
        }
    }

    private fun setFilter(
        @StringRes filteringLabelString: Int, @StringRes noDeviceInventorysLabelString: Int,
        @DrawableRes noDeviceInventoryIconDrawable: Int, tasksAddVisible: Boolean
    ) {
        _currentFilteringLabel.value = filteringLabelString
        _noDeviceInventoriesLabel.value = noDeviceInventorysLabelString
        _noDeviceInventoryIconRes.value = noDeviceInventoryIconDrawable
        _tasksAddViewVisible.value = tasksAddVisible
    }

    /**
     * Called by the Data Binding library and the FAB's click listener.
     */
    fun addNewDeviceInventory() {
        _newDeviceInventoryEvent.value = Event(Unit)
    }

    /**
     * Called by Data Binding.
     */
    fun openDeviceInventory(taskId: String) {
        _openDeviceInventoryEvent.value = Event(taskId)
    }

    private fun filterItems(
        inventories: List<DeviceInventory>,
        filteringType: DeviceStatus
    ): List<DeviceInventory> {
        val inventoriesToShow = ArrayList<DeviceInventory>()
        // We filter the tasks based on the requestType
        for (inventory in inventories) {
            when (filteringType) {
                DeviceStatus.ISSUED -> {
                    if (inventory.status == Utils.enumToIntDeviceStatus(DeviceStatus.ISSUED))
                        inventoriesToShow.add(inventory)
                }
                DeviceStatus.LOST -> {
                    if (inventory.status == Utils.enumToIntDeviceStatus(DeviceStatus.LOST))
                        inventoriesToShow.add(inventory)
                }
                DeviceStatus.RETURNED -> {
                    if (inventory.status == Utils.enumToIntDeviceStatus(DeviceStatus.RETURNED))
                        inventoriesToShow.add(inventory)
                }
                DeviceStatus.AVAILABLE -> {
                    inventoriesToShow.add(inventory)
                }
            }
        }
        return inventoriesToShow
    }
}