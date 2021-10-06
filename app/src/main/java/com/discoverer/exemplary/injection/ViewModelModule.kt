package com.discoverer.exemplary.injection

import com.discoverer.exemplary.viewmodel.MainViewModel
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Module that contains the MainViewModel, the viewmodel used by the Activities in this application.
 */
val viewModelModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}