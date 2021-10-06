package com.discoverer.exemplary.injection

import com.discoverer.exemplary.model.MainRepository
import org.koin.dsl.module


/**
 * Module that contains the main data repository used by this application.
 */
val repoModule = module {
    single {
        MainRepository(get())
    }
}
