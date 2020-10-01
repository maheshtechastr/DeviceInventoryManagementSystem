package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mpg.nagarro.deviceinventorymgmt.data.RepositoryImplTest
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import org.junit.Before
import org.junit.Rule
import java.util.*

class DeviceAllotmentViewModelTest{
    // Subject under test
    private lateinit var viewModel: DeviceAllottedListViewModel

    // Use a fake repository to be injected into the viewmodel
    private lateinit var repository: RepositoryImplTest

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        repository = RepositoryImplTest()
        val deviceInventory1 = DeviceInventory(1, Date(), Date(), 1, 1, "EmpName", "DevName", 1)
        val deviceInventory2 = DeviceInventory(1, Date(), Date(), 2, 1, "EmpName", "DevName", 2)
        val deviceInventory3 = DeviceInventory(1, Date(), Date(), 1, 2, "EmpName", "DevName", 3)
        repository.addDevInventory(deviceInventory1, deviceInventory2, deviceInventory3)

        viewModel = DeviceAllottedListViewModel(repository)
    }
}