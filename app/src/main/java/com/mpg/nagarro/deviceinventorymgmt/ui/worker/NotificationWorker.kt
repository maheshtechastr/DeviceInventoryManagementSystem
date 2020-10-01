package com.mpg.nagarro.deviceinventorymgmt.ui.worker

import android.content.Context
import android.util.Log
import androidx.work.*
import com.mpg.nagarro.deviceinventorymgmt.data.local.InventoryDao
import com.mpg.nagarro.deviceinventorymgmt.util.makeStatusNotification
import com.mpg.nagarro.deviceinventorymgmt.util.sleep

/**
 * Worker job to refresh titles from the network while the app is in the background.
 *
 * WorkManager is a library used to enqueue work that is guaranteed to execute after its constraints
 * are met. It can run work even when the app is in the background, or not running.
 */
class NotificationWorker(
    val context: Context,
    params: WorkerParameters,
    private val dao: InventoryDao
) :
    CoroutineWorker(context, params) {

    /**
     * Refresh the title from the network using [TitleRepository]
     *
     * WorkManager will call this method from a background thread. It may be called even
     * after our app has been terminated by the operating system, in which case [WorkManager] will
     * start just enough to run this [Worker].
     */
    override suspend fun doWork(): Result {

        return try {
            val inventories = dao.getDeviceInventories()
            Log.i("TAG", "onCreate=: doWork dao= $dao")
            Log.i("TAG", "onCreate=: doWork inventories = $inventories")

            inventories.forEach { item ->
                if (item.issueDate.time > System.currentTimeMillis()) {
                    makeStatusNotification(
                        "Device ${item.devName} is due on ${item.empName}",
                        context
                    )
                    sleep()
                }
            }
            Result.success()
        } catch (error: Exception) {
            Result.failure()
        }
    }

    class Factory(val dao: InventoryDao) : WorkerFactory() {
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker? {
            return NotificationWorker(appContext, workerParameters, dao)
        }

    }
}