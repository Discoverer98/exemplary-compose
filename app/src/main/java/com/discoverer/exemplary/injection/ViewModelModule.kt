package com.discoverer.exemplary.injection

import com.discoverer.exemplary.viewmodel.MainViewModel
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}