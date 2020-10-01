package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mpg.nagarro.deviceinventorymgmt.data.RepositoryImplTest
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddEditDeviceViewModelTest {

    // Subject under test
    private lateinit var viewModel: AddEditDeviceViewModel

    // Use a fake repository to be injected into the viewmodel
    private lateinit var repository: RepositoryImplTest

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        repository = RepositoryImplTest()
        val deviceEntity1 = DeviceEntity("Title1", 2, 2, 1)
        val deviceEntity2 = DeviceEntity("Title2", 3, 3, 2)
        val deviceEntity3 = DeviceEntity("Title3", 2, 2, 3)
        repository.addDevice(deviceEntity1, deviceEntity2, deviceEntity3)

        viewModel = AddEditDeviceViewModel(repository)
    }
    @Test
    fun addNewDevice_setsNewTaskEvent() {
        // When adding a new task
//        viewModel.addNewTask()
        viewModel.addDevice()
//        // Then the new task event is triggered
//        val value = viewModel.newTaskEvent.getOrAwaitValue()
//
//        MatcherAssert.assertThat(
//            value.getContentIfNotHandled(),
//            CoreMatchers.not(CoreMatchers.nullValue())
//        )


    }
}