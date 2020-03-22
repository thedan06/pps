package com.dustanmbaga.pps.acaricide

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.dustanmbaga.pps.getDatabase

/**
 * Worker job to refresh acaricides from the network while the app is in the background.
 *
 * WorkManager is a library used to enqueue work that is guaranteed to execute after its constraints
 * are met. It can run work even when the app is in the background, or not running.
 */
class RefreshAcaricideDataWork(context: Context, params: WorkerParameters, private val network: AcaricideNetwork) :
        CoroutineWorker(context, params) {

    /**
     * Refresh the acaricide from the network using [AcaricideRepository]
     *
     * WorkManager will call this method from a background thread. It may be called even
     * after our app has been terminated by the operating system, in which case [WorkManager] will
     * start just enough to run this [Worker].
     */
    override suspend fun doWork(): Result {
        val database =
            getDatabase(applicationContext)
        val repository = AcaricideRepository(
            network,
            database.acaricideDao
        )

        return try {
            repository.refreshAcaricide()
            Result.success()
        } catch (error: AcaricideRefreshError) {
            Result.failure()
        }
    }

    class Factory(val network: AcaricideNetwork = getNetworkService()) : WorkerFactory() {
        override fun createWorker(appContext: Context, workerClassName: String, workerParameters: WorkerParameters): ListenableWorker? {
            return RefreshAcaricideDataWork(
                appContext,
                workerParameters,
                network
            )
        }

    }
}