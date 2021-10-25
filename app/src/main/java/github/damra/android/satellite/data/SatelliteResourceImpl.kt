package github.damra.android.satellite.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import github.damra.android.satellite.model.SatelliteDetail
import github.damra.android.satellite.model.SatelliteListItem
import github.damra.android.satellite.model.SatellitePositionList
import github.damra.android.satellite.util.Utils

open class SatelliteResourceImpl(var context: Context, var utils: Utils) : SatelliteResource {
    override suspend fun getSatelliteList(): List<SatelliteListItem> {
        val jsonString = utils.getJson(context, "satellite_list.json")
        val listSatelliteType = object : TypeToken<List<SatelliteListItem>>() {}.type
        return Gson().fromJson(jsonString, listSatelliteType)
    }

    override suspend fun getSatelliteDetailList(): List<SatelliteDetail> {
        val jsonString = utils.getJson(context, "satellite_detail.json")
        val listSatelliteDetailType = object : TypeToken<List<SatelliteDetail>>() {}.type
        return Gson().fromJson(jsonString, listSatelliteDetailType)
    }

    override suspend fun getSatellitePositionList(): SatellitePositionList {
        val jsonString = utils.getJson(context, "positions.json")
        val listSatellitePositionType = object : TypeToken<SatellitePositionList>() {}.type
        return Gson().fromJson(jsonString, listSatellitePositionType)
    }
}