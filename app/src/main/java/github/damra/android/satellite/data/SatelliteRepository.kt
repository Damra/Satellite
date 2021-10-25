package github.damra.android.satellite.data

import android.util.Log
import github.damra.android.satellite.model.SatelliteDetail
import github.damra.android.satellite.model.SatelliteListItem
import github.damra.android.satellite.model.SatellitePositionItem
import github.damra.android.satellite.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.logger.KOIN_TAG
import java.util.*

class SatelliteRepository(private var satelliteResourceImpl: SatelliteResourceImpl) {

    companion object {
        private val loadedSatelliteDetail = HashMap<Int, SatelliteDetail>()
    }

    fun getSatelliteList(): Flow<Resource<List<SatelliteListItem>>> = flow {
        try {
            emit(Resource.success(satelliteResourceImpl.getSatelliteList()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    @Synchronized
    fun getSatelliteDetailById(id: Int): Flow<Resource<SatelliteDetail>> = flow {
        if (loadedSatelliteDetail.containsKey(id)) {
            Log.d(KOIN_TAG, "getSatelliteDetailById: 1")
            try {
                emit(Resource.success(loadedSatelliteDetail[id]!!))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.error(data = null, message = e.message ?: "Error"))
            }
        } else {
            Log.d(KOIN_TAG, "getSatelliteDetailById: 2")

            try {
                satelliteResourceImpl.getSatelliteDetailList().find { it.id == id }?.let {
                    loadedSatelliteDetail[id] = it
                    emit(Resource.success(it))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.error(data = null, message = e.message ?: "Error"))
            }
        }
    }

    fun searchSatelliteByName(term: String): Flow<Resource<List<SatelliteListItem>>> = flow {
        try {
            Log.d(KOIN_TAG, "searchSatelliteByName: $term")
            val data = satelliteResourceImpl.getSatelliteList().filter {
                it.name.lowercase(Locale.getDefault()).contains(term)
            }
            emit(Resource.success(data = data))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    fun getSatellitePosition(id: Int): Flow<Resource<SatellitePositionItem>> = flow {
        try {
            while (true) {
                Log.d(KOIN_TAG, "getSatellitePosition: ")
                kotlinx.coroutines.delay(3000)
                val data = satelliteResourceImpl.getSatellitePositionList().list.find {
                    it.id == id
                }!!
                emit(Resource.success(data))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }
}