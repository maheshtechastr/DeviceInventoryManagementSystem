package com.mpg.nagarro.deviceinventorymgmt.ui.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    val employees: LiveData<List<EmployeeEntity>> = repository.getEmployeeList()

    fun deleteRow(employeeEntity: EmployeeEntity) = viewModelScope.launch {
        repository.deleteEmployee(employeeEntity.empId)
    }
}