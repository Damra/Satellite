package github.damra.android.satellite.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SatellitePosition(var posX: Double, var posY: Double) : Parcelable

@Parcelize
data class SatellitePositionItem(var id: Int, var positions: List<SatellitePosition>) : Parcelable

@Parcelize
data class SatellitePositionList(var list: List<SatellitePositionItem>) : Parcelable