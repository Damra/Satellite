package github.damra.android.satellite.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SatelliteDetail(
    var id: Int,
    var cost_per_launch: Int,
    val first_flight: String,
    var height: Int,
    var mass: Int
) : Parcelable