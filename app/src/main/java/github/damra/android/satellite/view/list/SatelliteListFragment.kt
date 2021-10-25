package github.damra.android.satellite.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawegio.kandroid.textWatcher
import github.damra.android.satellite.R
import github.damra.android.satellite.model.SatelliteListItem
import kotlinx.android.synthetic.main.fragment_satellite_list.*
import org.koin.android.ext.android.inject


class SatelliteListFragment : Fragment() {

    private val viewModel: SatelliteListViewModel by inject()
    private val recyclerViewAdapter = SatelliteRecyclerViewAdapter(::onListItemClicked, ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_satellite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSatelliteLiveData()
        setupRecyclerView()
        setupQueryTextWatcher()
    }

    private fun observeSatelliteLiveData() {
        viewModel.loadingValue.observe(viewLifecycleOwner, {
            progressBar.isVisible = it
        })
        viewModel.successValue.observe(viewLifecycleOwner, {

        })
        viewModel.errorValue.observe(viewLifecycleOwner, {

        })
        viewModel.satellites.observe(viewLifecycleOwner, {
            tvEmpty.isVisible = it.isEmpty()
            rvSatellite.isVisible = it.isNotEmpty()
            recyclerViewAdapter.loadData(it)
        })
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.fetchSatelliteList().observe(viewLifecycleOwner, {
            viewModel.resourceStatusData(it)
        })
    }

    private fun setupRecyclerView() {
        rvSatellite.layoutManager = LinearLayoutManager(activity)
        rvSatellite.adapter = recyclerViewAdapter
        rvSatellite.itemAnimator = DefaultItemAnimator()
        rvSatellite.setHasFixedSize(true)

        rvSatellite.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setupQueryTextWatcher() {
        editTextSearchQuery?.apply {
            textWatcher {
                afterTextChanged {
                    doSearch(text.toString())
                }
            }
        }
    }

    private fun doSearch(term: String) {
        if (term.trim().isEmpty()) {
            viewModel.fetchSatelliteList().observe(viewLifecycleOwner, {
                viewModel.resourceStatusData(it)
            })
        } else {
            viewModel.searchSatelliteName(term.trim()).observe(viewLifecycleOwner, {
                viewModel.resourceStatusData(it)
            })
        }
    }

    private fun onListItemClicked(satelliteListItem: SatelliteListItem) {
        viewModel.fetchSatelliteDetailById(satelliteListItem.id).observe(viewLifecycleOwner) {
            it.data?.let {
                val bundle = Bundle()
                bundle.putParcelable("satelliteDetail", it)
                bundle.putParcelable("satelliteListItem", satelliteListItem)
                findNavController(requireView()).navigate(
                    R.id.action_homeFragment_to_detailFragment,
                    bundle
                )
            }
        }
    }
}