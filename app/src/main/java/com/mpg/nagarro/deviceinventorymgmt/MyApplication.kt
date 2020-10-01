package com.mpg.nagarro.deviceinventorymgmt

import android.app.Activity
import android.app.Application
import androidx.work.*
import com.mpg.nagarro.deviceinventorymgmt.data.local.InventoryDao
import com.mpg.nagarro.deviceinventorymgmt.di.DaggerAppComponent
import com.mpg.nagarro.deviceinventorymgmt.ui.worker.NotificationWorker
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dao: InventoryDao

    lateinit var workManager: WorkManager

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        setupWorkManagerJob()
    }

    /** Returns an [AndroidInjector] of [Activity]s.  */
    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return androidInjector
    }

    /**
     * Setup WorkManager background job to 'fetch' new network data daily.
     */
    private fun setupWorkManagerJob() {
        // initialize WorkManager with a Factory
        val workManagerConfiguration = Configuration.Builder()
            .setWorkerFactory(NotificationWorker.Factory(dao))
            .build()
        WorkManager.initialize(this, workManagerConfiguration)

        // Use constraints to require the work only run when the device is charging and the
        // network is unmetered
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        // Specify that the work should attempt to run every day
        val work = PeriodicWorkRequestBuilder<NotificationWorker>(2, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        // Enqueue it work WorkManager, keeping any previously scheduled jobs for the same
        // work.
        workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(NotificationWorker::class.java.name,
                ExistingPeriodicWorkPolicy.KEEP, work)
    }
}