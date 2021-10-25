package github.damra.android.satellite.data

import github.damra.android.satellite.model.SatelliteDetail
import github.damra.android.satellite.model.SatelliteListItem
import github.damra.android.satellite.model.SatellitePositionList

interface SatelliteResource {
    suspend fun getSatelliteList(): List<SatelliteListItem>
    suspend fun getSatelliteDetailList(): List<SatelliteDetail>
    suspend fun getSatellitePositionList(): SatellitePositionList
}