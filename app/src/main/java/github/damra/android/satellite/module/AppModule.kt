package github.damra.android.satellite.module

import github.damra.android.satellite.data.SatelliteRepository
import github.damra.android.satellite.data.SatelliteResourceImpl
import github.damra.android.satellite.util.Utils
import github.damra.android.satellite.view.detail.SatelliteDetailViewModel
import github.damra.android.satellite.view.list.SatelliteListViewModel
import org.koin.dsl.module

var appModule = module {
    single { Utils() }
    single { SatelliteRepository(get()) }
    single { SatelliteResourceImpl(context = get(), utils = get()) }
    factory { SatelliteListViewModel(get()) }
    factory { SatelliteDetailViewModel(get()) }
}