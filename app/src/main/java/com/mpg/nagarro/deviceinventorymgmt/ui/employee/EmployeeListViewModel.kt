package com.mpg.nagarro.deviceinventorymgmt.ui.employee

import androidx.lifecycle.LiveData
import com.mpg.nagarro.deviceinventorymgmt.base.BaseViewModel
import com.mpg.nagarro.deviceinventorymgmt.data.Repository
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import javax.inject.Inject

class EmployeeListViewModel @Inject constructor(repository: Repository) : BaseViewModel() {
    val employees : LiveData<List<EmployeeEntity>> = repository.getEmployeeList()

}