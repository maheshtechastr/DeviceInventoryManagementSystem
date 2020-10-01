package com.mpg.nagarro.deviceinventorymgmt.ui.employee

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mpg.nagarro.deviceinventorymgmt.data.RepositoryImplTest
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import org.junit.Before
import org.junit.Rule

class AddEditEmployeeViewModelTest{
    // Subject under test
    private lateinit var viewModel: EmployeeListViewModel

    // Use a fake repository to be injected into the viewmodel
    private lateinit var repository: RepositoryImplTest

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        repository = RepositoryImplTest()
        val employee1 = EmployeeEntity("Name1", 1, "email1@abc.com")
        val employee2 = EmployeeEntity("Name2", 1, "email2@abc.com")
        val employee3 = EmployeeEntity("Name3", 1, "email3@abc.com")
        repository.addEmployee(employee1, employee2, employee3)

        viewModel = EmployeeListViewModel(repository)
    }

}