package br.com.dio.trademapclone.di

import br.com.dio.trademapclone.AppDatabase
import br.com.dio.trademapclone.MainViewModel
import br.com.dio.trademapclone.RetrofitService
import br.com.dio.trademapclone.repository.AcaoRepository
import br.com.dio.trademapclone.ui.AcaoViewModel
import br.com.dio.trademapclone.ui.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { AcaoViewModel(get()) }
}

val serviceModule = module {
    single { RetrofitService.createService() }
}

val repositoryModule = module {
    single { AcaoRepository(get(), get()) }
}

val daoModule = module {
    single { AppDatabase.getInstance(androidContext()).acaoDAO() }
}