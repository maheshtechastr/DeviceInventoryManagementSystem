package com.mpg.nagarro.deviceinventorymgmt.ui.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mpg.nagarro.deviceinventorymgmt.util.ExportDbUtil
import com.mpg.nagarro.deviceinventorymgmt.util.OUTPUT_PATH
import kotlinx.coroutines.coroutineScope


/**
 * IMPORTANT NOTE!
 *
 * The [Context] need to be named with [appContext] and [WorkerParameters] with [params]
 * as long as these name are identical with [ChildWorkerFactory.create]'s method parameters
 *
 */
class ScheduleBackupWorker constructor(
    private val appContext: Context,
    private val params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    private val TAG = "HelloWorldWorker"
    lateinit var exportDbUtil: ExportDbUtil

    override suspend fun doWork(): Result = coroutineScope{

        Log.d(TAG, "onCreate= Injected dao: ")
        exportDbUtil = ExportDbUtil(appContext, "InventoryDatabase.db", OUTPUT_PATH)
        exportDbUtil.exportSingleTable("DeviceEntity","DeviceEntity_${System.currentTimeMillis()}")
        exportDbUtil.exportSingleTable("EmployeeEntity","EmployeeEntity_${System.currentTimeMillis()}")
        exportDbUtil.exportSingleTable("DeviceInventory","DeviceInventory_${System.currentTimeMillis()}")
        Log.d(TAG, "onCreate= Hello world!===${exportDbUtil.isBackupExist(OUTPUT_PATH)}")
        return@coroutineScope Result.success()
    }

}