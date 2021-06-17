package com.dicoding.film.favorite.di

import com.dicoding.film.favorite.ui.favorite.film.FilmFavoriteViewModel
import com.dicoding.film.favorite.ui.favorite.tvshow.TvShowFavoriteViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel


val favoritesModule = module {
    viewModel { FilmFavoriteViewModel(get()) }
    viewModel { TvShowFavoriteViewModel(get()) }

}