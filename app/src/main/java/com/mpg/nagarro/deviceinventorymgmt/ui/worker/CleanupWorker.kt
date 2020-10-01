package com.mpg.nagarro.deviceinventorymgmt.ui.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mpg.nagarro.deviceinventorymgmt.util.OUTPUT_PATH
import com.mpg.nagarro.deviceinventorymgmt.util.Utils
import java.io.File

/**
 * Cleans up temporary files generated during blurring process
 */

class CleanupWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    private val TAG = "CleanupWorker"
    override fun doWork(): Result {
        Log.i(TAG, "onCreate= : doWork====>")
        return try {
            val outputDirectory = File(applicationContext.filesDir, OUTPUT_PATH)
            if (outputDirectory.exists()) {
                val entries = outputDirectory.listFiles()
                if (entries != null) {
                    for (entry in entries) {
                        val name = entry.name
                        if (name.isNotEmpty() && name.endsWith(".csv")) {
                            val startIn = name.indexOf("_")
                            val timeInSec = name.substring(startIn, (name.length - 4)).toLong()

                            val numDays = Utils.getDifferenceInDays(timeInSec)

                            if (numDays >= 30) {
                                val deleted = entry.delete()
                                Log.i(TAG, "Deleted $name - $deleted")
                            }
                            Log.i(TAG, "name: Cleanup worker = $name")
                        }
                    }
                }
            }
            Result.success()
        } catch (exception: Exception) {
            //Timber.e(exception)
            Result.failure()
        }
    }
}