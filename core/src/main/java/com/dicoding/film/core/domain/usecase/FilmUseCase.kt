package com.dicoding.film.core.domain.usecase

import com.dicoding.film.core.domain.model.Film
import com.dicoding.film.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface FilmUseCase{
    fun getAllFilm(): Flow<Resource<List<Film>>>

    fun getAllTvShow(): Flow<Resource<List<Film>>>

    fun getDetailFilm(id: Int): Flow<Resource<Film>>

    fun getDetailTv(id:Int): Flow<Resource<Film>>

    fun getFavoritedFilm(): Flow<List<Film>>

    fun getFavoritedTvShow(): Flow<List<Film>>

    fun setFavoriteFilm(film: Film, state: Boolean)

    fun setFavoriteTvShow(tv: Film, state: Boolean)

}