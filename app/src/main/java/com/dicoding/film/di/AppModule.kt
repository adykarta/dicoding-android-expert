package com.dicoding.film.di

import com.dicoding.film.core.domain.usecase.FilmInteractor
import com.dicoding.film.core.domain.usecase.FilmUseCase
import com.dicoding.film.ui.detail.DetailFilmViewModel
import com.dicoding.film.ui.home.film.FilmViewModel
import com.dicoding.film.ui.home.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FilmUseCase> { FilmInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailFilmViewModel(get()) }
    viewModel { FilmViewModel(get()) }
    viewModel { TvShowViewModel(get()) }

}