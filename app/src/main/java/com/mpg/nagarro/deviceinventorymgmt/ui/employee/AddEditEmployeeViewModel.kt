package com.mpg.nagarro.deviceinventorymgmt.ui.employee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import com.mpg.nagarro.deviceinventorymgmt.util.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditEmployeeViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    val empEmail = MutableLiveData<String>()
    val empName = MutableLiveData<String>()

    fun addEmployee() {
        val employeeName = empName.value
        val employeeEmail = empEmail.value

        if (Utils.isNullOrEmpty(employeeName) || Utils.isNullOrEmpty(employeeEmail))
            return

        if (Utils.isValidEmail(employeeEmail!!))
            viewModelScope.launch {
                repository.addEmployee(EmployeeEntity(empName.value!!, employeeEmail))
            }
    }
}