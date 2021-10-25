package github.damra.android.satellite.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import github.damra.android.satellite.R
import github.damra.android.satellite.model.SatelliteDetail
import github.damra.android.satellite.model.SatelliteListItem
import github.damra.android.satellite.util.fromHtml
import kotlinx.android.synthetic.main.fragment_satellite_detail.*
import org.koin.android.ext.android.inject


class SatelliteDetailFragment : Fragment() {

    private val viewModel: SatelliteDetailViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_satellite_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSatelliteLiveData()

        var satelliteListItem: SatelliteListItem? = null
        var satelliteDetail: SatelliteDetail? = null

        arguments?.let {
            satelliteDetail = SatelliteDetailFragmentArgs.fromBundle(it).satelliteDetail
            satelliteListItem = SatelliteDetailFragmentArgs.fromBundle(it).satelliteListItem
        }

        satelliteListItem?.let {
            tvSatelliteName?.text = it.name
        }

        satelliteDetail?.let { it ->
            tvFirstFlightDate?.text = it.first_flight
            tvHeightMass?.fromHtml(
                context?.getString(R.string.fragment_satellite_detail_height_mass)
                    ?.format(it.height, it.mass)!!
            )
            tvCost?.fromHtml(
                context?.getString(R.string.fragment_satellite_detail_cost)
                    ?.format(it.cost_per_launch)!!
            )

            viewModel.fetchSatellitePosition(it.id).observe(viewLifecycleOwner, {
                viewModel.resourceStatusData(it)
            })
        }
    }

    private fun observeSatelliteLiveData() {
        viewModel.loadingValue.observe(viewLifecycleOwner, {
            progressBar.isVisible = it
        })

        viewModel.successValue.observe(viewLifecycleOwner, {

        })

        viewModel.errorValue.observe(viewLifecycleOwner, {

        })

        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.positions.observe(viewLifecycleOwner, {
            val randomPos = it.positions.random()
            tvLastPosition?.fromHtml(
                context?.getString(R.string.fragment_satellite_last_position)
                    ?.format(randomPos.posX, randomPos.posY)!!
            )
        })
    }

}