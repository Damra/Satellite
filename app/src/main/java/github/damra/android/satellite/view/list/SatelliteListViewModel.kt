package github.damra.android.satellite.view.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import github.damra.android.satellite.data.SatelliteRepository
import github.damra.android.satellite.model.SatelliteDetail
import github.damra.android.satellite.model.SatelliteListItem
import github.damra.android.satellite.util.Resource
import github.damra.android.satellite.util.Status
import kotlinx.coroutines.flow.onStart
import org.koin.core.logger.KOIN_TAG


class SatelliteListViewModel(private val satelliteRepository: SatelliteRepository) : ViewModel() {

    private val _loadingValue = MutableLiveData<Boolean>()
    val loadingValue: LiveData<Boolean>
        get() = _loadingValue

    private val _successValue = MutableLiveData<Boolean>()
    val successValue: LiveData<Boolean>
        get() = _successValue

    private val _errorValue = MutableLiveData<Boolean>()
    val errorValue: LiveData<Boolean>
        get() = _errorValue

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _satellites = MutableLiveData<List<SatelliteListItem>>()
    val satellites: LiveData<List<SatelliteListItem>>
        get() = _satellites


    fun fetchSatelliteList(): LiveData<Resource<List<SatelliteListItem>>> =
        satelliteRepository.getSatelliteList()
            .onStart { emit(Resource.loading(data = null)) }
            .asLiveData()


    fun searchSatelliteName(term: String): LiveData<Resource<List<SatelliteListItem>>> =
        satelliteRepository.searchSatelliteByName(term)
            .onStart { emit(Resource.loading(data = null)) }
            .asLiveData()

    @Synchronized
    fun fetchSatelliteDetailById(id: Int): LiveData<Resource<SatelliteDetail>> =
        satelliteRepository.getSatelliteDetailById(id)
            .onStart { emit(Resource.loading(data = null)) }
            .asLiveData()


    fun resourceStatusData(resource: Resource<List<SatelliteListItem>>) {
        when (resource.status) {
            Status.LOADING -> {
                Log.e(KOIN_TAG, "Loading")
                _loadingValue.value = true
                _successValue.value = false
                _errorValue.value = false
            }
            Status.SUCCESS -> {
                Log.e(KOIN_TAG, "Success")
                resource.data?.let { _satellites.value = it }
                _loadingValue.value = false
                _successValue.value = true
                _errorValue.value = false
            }
            Status.ERROR -> {
                resource.message
                Log.e(KOIN_TAG, "Error")
                _error.value = "Error ${resource.message} "
                _loadingValue.value = false
                _successValue.value = false
                _errorValue.value = true
            }
        }
    }
}