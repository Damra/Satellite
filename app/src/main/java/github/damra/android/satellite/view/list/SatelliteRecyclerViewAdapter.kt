package github.damra.android.satellite.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import github.damra.android.satellite.R
import github.damra.android.satellite.model.SatelliteListItem
import github.damra.android.satellite.util.then

class SatelliteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var rootContainer: FrameLayout = view.findViewById(R.id.rootContainer)
    var civStatus: CircleImageView = view.findViewById(R.id.civStatus)
    var tvName: TextView = view.findViewById(R.id.tvName)
    var tvStatus: TextView = view.findViewById(R.id.tvStatus)
}

class SatelliteRecyclerViewAdapter(
    private val listener: (SatelliteListItem) -> Unit,
    private var satellites: List<SatelliteListItem>
) :
    RecyclerView.Adapter<SatelliteViewHolder>() {

    fun loadData(newSatellites: List<SatelliteListItem>) {
        satellites = newSatellites
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_satellite_list_item, parent, false)
        return SatelliteViewHolder(view)
    }

    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        val satellite = satellites[position]

        holder.rootContainer.setOnClickListener { listener.invoke(satellite) }

        holder.tvName.isEnabled = satellite.active
        holder.tvStatus.isEnabled = satellite.active
        holder.tvName.text = satellite.name
        holder.civStatus.setImageResource(satellite.active then { R.color.green } ?: R.color.red)
        holder.tvStatus.setText(satellite.active then { R.string.satellite_active }
            ?: R.string.satellite_passive)
    }

    override fun getItemCount(): Int {
        return satellites.size
    }
}