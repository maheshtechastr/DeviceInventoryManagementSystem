package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mpg.nagarro.deviceinventorymgmt.data.RepositoryImplTest
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import timber.log.Timber

@ExperimentalCoroutinesApi
class AddEditDeviceViewModelTest {

    val FAKE_STRING: String = "Filled can't be empty"

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
    fun addNewDevice_CheckValueForNull() = runBlocking {
        viewModel.deviceName.value = null
        viewModel.totalInventory.value = "2"
        // When adding a new task
        viewModel.addDevice()

        Assert.assertTrue(viewModel.showMessage.value.equals(FAKE_STRING))
        Timber.i("Device == ${viewModel.deviceName}")

    }

    @Test
    fun addNewDevice_CheckValueForNotNull() = runBlockingTest {
        viewModel.deviceName.value = "Device1"
        viewModel.totalInventory.value = ""
        // When adding a new task
        viewModel.addDevice()
        Assert.assertFalse(viewModel.showMessage.value.equals(FAKE_STRING))
        Timber.i("Device == ${viewModel.deviceName}")
    }

    @Test
    fun addNewDevice_CheckWithValueForTotalCountNull() = runBlockingTest {
        viewModel.deviceName.value = "Device1"
        viewModel.totalInventory.value = ""
        // When adding a new task
        viewModel.addDevice()
        Assert.assertTrue(viewModel.totalInventory.value == "1")
        Timber.i("Device == ${viewModel.deviceName}")
    }

    @Test
    fun addNewDevice_CheckWithValueForTotalCountNotNull() = runBlockingTest {
        viewModel.deviceName.value = "Device1"
        viewModel.totalInventory.value = "2"
        // When adding a new task
        viewModel.addDevice()

        Assert.assertTrue(viewModel.totalInventory.value == "2")
        Timber.i("${viewModel.totalInventory.value}==Device == ${viewModel.deviceName}")
    }
}