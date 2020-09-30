package com.mpg.nagarro.deviceinventorymgmt.ui.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mpg.nagarro.deviceinventorymgmt.util.OUTPUT_PATH
import com.mpg.nagarro.deviceinventorymgmt.util.makeStatusNotification
import com.mpg.nagarro.deviceinventorymgmt.util.sleep
import timber.log.Timber
import java.io.File

/**
 * Cleans up temporary files generated during blurring process
 */

class CleanupWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    private val TAG = "CleanupWorker"
    override fun doWork(): Result {
        // Makes a notification when the work starts and slows down the work so that
        // it's easier to see each WorkRequest start, even on emulated devices
        makeStatusNotification("Cleaning up 30days older Backup files", applicationContext)
        sleep()
        Log.i(TAG, "onCreate= : doWork====>")
        return try {
            val outputDirectory = File(applicationContext.filesDir, OUTPUT_PATH)
            if (outputDirectory.exists()) {
                val entries = outputDirectory.listFiles()
                if (entries != null) {
                    for (entry in entries) {
                        val name = entry.name
                        if (name.isNotEmpty() && name.endsWith(".csv")) {
                            val deleted = entry.delete()
                            Log.i(TAG,"Deleted $name - $deleted")
                        }
                    }
                }
            }
            Result.success()
        } catch (exception: Exception) {
            Timber.e(exception)
            Result.failure()
        }
    }
}