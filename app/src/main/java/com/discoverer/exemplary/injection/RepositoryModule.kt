package com.discoverer.exemplary.injection

import com.discoverer.exemplary.model.MainRepository
import org.koin.dsl.module


val repoModule = module {
    single {
        MainRepository(get())
    }
}
