package github.damra.android.satellite.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SatelliteListItem(var id: Int, var active: Boolean, var name: String) : Parcelable