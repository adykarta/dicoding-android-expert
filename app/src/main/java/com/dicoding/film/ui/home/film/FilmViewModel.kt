package com.dicoding.film.ui.home.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.film.core.domain.model.Film
import com.dicoding.film.core.domain.usecase.FilmUseCase
import com.dicoding.film.core.vo.Resource


class FilmViewModel(private val filmUseCase: FilmUseCase): ViewModel() {
    fun getFilm(): LiveData<Resource<List<Film>>> = filmUseCase.getAllFilm().asLiveData()

}