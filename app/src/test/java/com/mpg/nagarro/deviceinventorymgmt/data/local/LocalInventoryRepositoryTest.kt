package com.mpg.nagarro.deviceinventorymgmt.data.local

import com.mpg.nagarro.deviceinventorymgmt.data.FakeDataSource
import com.mpg.nagarro.deviceinventorymgmt.data.RepositoryImpl
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.data.entity.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalInventoryRepositoryTest {

    private val deviceEntity1 = DeviceEntity("Title1", 2, 2, 1)
//    private val deviceEntity2 = DeviceEntity("Title2", 3, 3, 2)
//    private val deviceEntity3 = DeviceEntity("Title3", 2, 2, 3)

    private val localTasks = listOf(deviceEntity1)
        .sortedBy { it.deviceId }

    private lateinit var tasksLocalDataSource: FakeDataSource

    // Class under test
    private lateinit var repositoryImpl: RepositoryImpl

    @Before
    fun createRepository() {
        tasksLocalDataSource = FakeDataSource(localTasks.toMutableList())
        // Get a reference to the class under test
        repositoryImpl = RepositoryImpl(
            tasksLocalDataSource
        )
    }

    @Test
    fun getTasks_requestsAllTasksFromLocalDataSource() = runBlockingTest {
        // When tasks are requested from the tasks repository
        val tasks = repositoryImpl.getDevices() as Result.Success

        // Then tasks are loaded from the remote data source
        Assert.assertThat(tasks.data, IsEqual(localTasks))
    }


}