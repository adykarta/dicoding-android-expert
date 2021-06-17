package com.dicoding.film.favorite.ui.favorite.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.film.core.domain.model.Film
import com.dicoding.film.core.domain.usecase.FilmUseCase

class FilmFavoriteViewModel(private val filmUseCase: FilmUseCase): ViewModel() {
    fun getFavoriteFilm(): LiveData<List<Film>> = filmUseCase.getFavoritedFilm().asLiveData()

    fun setFavorite(film: Film) {
        val newState = !film.favorited
        filmUseCase.setFavoriteFilm(film, newState)
    }
}