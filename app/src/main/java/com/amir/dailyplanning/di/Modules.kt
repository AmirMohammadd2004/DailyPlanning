package com.amir.dailyplanning.di

import com.amir.dailyplanning.model.net.createApiService
import com.amir.dailyplanning.model.repository.PlaningRepository
import com.amir.dailyplanning.model.repository.PlaningRepositoryIMP
import com.amir.dailyplanning.ui.features.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val myModules = module {

    //ApiService
    single { createApiService() }


    //Repository
    single<PlaningRepository> { PlaningRepositoryIMP(get()) }



    viewModel{ HomeScreenViewModel(get()) }

}