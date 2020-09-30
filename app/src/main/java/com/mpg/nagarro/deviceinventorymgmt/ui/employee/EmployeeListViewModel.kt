package com.mpg.nagarro.deviceinventorymgmt.ui.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    val employees: LiveData<List<EmployeeEntity>> = repository.getEmployeeList()

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(employees) {
        it.isEmpty()
    }

    fun deleteRow(entity: EmployeeEntity) = viewModelScope.launch {
        val deviceInventories = repository.getAllIssuedOrLostInventoryOfEmpId(entity.empId)
        if (deviceInventories.isEmpty())
            repository.deleteEmployee(entity.empId)
        else
            showMessage.postValue("\" ${entity.name} \" can't be deleted \n He/She has not  returned the devices or lost")
    }
}