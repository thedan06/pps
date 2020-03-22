package com.dustanmbaga.pps.acaricide

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * AcaricideRepository provides an interface to fetch a acaricides or request a new one be generated.
 *
 * Repository modules handle data operations. They provide a clean API so that the rest of the app
 * can retrieve this data easily. They know where to get the data from and what API calls to make
 * when data is updated. You can consider repositories to be mediators between different data
 * sources, in our case it mediates between a network API and an offline database cache.
 */
class AcaricideRepository(val network: AcaricideNetwork, val acaricideDao: AcaricideDao) {
    /**
     * [LiveData] to load acaricide.
     *
     * This is the main interface for loading a acaricide. The acaricide will be loaded from the offline
     * cache.
     *
     * Observing this will not cause the acaricide to be refreshed, use [AcaricideRepository.refreshAcaricide]
     * to refresh the acaricide.
     */    
    val acaricides: LiveData<Array<Acaricide>?> = acaricideDao.acaricidesLiveData

    /**
     * Refresh the current acaricide and save the results to the offline cache.
     *
     * This method does not return the new acaricide. Use [AcaricideRepository.acaricides] to observe
     * the current acaricides.
     */
    suspend fun refreshAcaricide() {
        try {
            
            // TODO: before inserting, count number of entries in online db to check it there are newly added items
            val result = network.fetchAcaricides()
            acaricideDao.insertAll(result)
            
        } catch (error: Throwable) {
            throw AcaricideRefreshError("Unable to refresh Acaricides", error)
        }
    }

    /**
     * This API is exposed for callers from the Java Programming language.
     *
     * The request will run unstructured, which means it won't be able to be cancelled.
     *
     * @param acaricideRefreshCallback a callback
     */
    fun refreshAcaricideInterop(acaricideRefreshCallback: AcaricideRefreshCallback) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            try {
                refreshAcaricide()
                acaricideRefreshCallback.onCompleted()
            } catch (throwable: Throwable) {
                acaricideRefreshCallback.onError(throwable)
            }
        }
    }
    
}

/**
 * Thrown when there was a error fetching a new acaricide
 *
 * @property message user ready error message
 * @property cause the original cause of this exception
 */
class AcaricideRefreshError(message: String, cause: Throwable) : Throwable(message, cause)

interface AcaricideRefreshCallback {
    fun onCompleted()
    fun onError(cause: Throwable)
}