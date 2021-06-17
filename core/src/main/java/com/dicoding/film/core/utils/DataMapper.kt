package com.dicoding.film.core.utils

import com.dicoding.film.core.data.source.local.entity.FilmEntity
import com.dicoding.film.core.domain.model.Film

object DataMapper {

    fun mapEntitiesToDomain(input: List<FilmEntity>): List<Film> =
        input.map {
            Film(
                id =  it.id,
                title =  it.title,
                genre = it.genre,
                overview = it.overview,
                userScore = it.userScore,
                releaseYear = it.releaseYear,
                duration = it.duration,
                photo = it.photo,
                favorited = it.favorited,
                type = it.type

            )
        }
    fun convertEntityToDomain(input:FilmEntity): Film = Film(
        id =  input.id,
        title =  input.title,
        genre = input.genre,
        overview = input.overview,
        userScore = input.userScore,
        releaseYear = input.releaseYear,
        duration = input.duration,
        photo = input.photo,
        favorited = input.favorited,
        type = input.type

    )
    fun mapDomainToEntity(input:Film) =FilmEntity(
        id =  input.id,
        title =  input.title,
        genre = input.genre,
        overview = input.overview,
        userScore = input.userScore,
        releaseYear = input.releaseYear,
        duration = input.duration,
        photo = input.photo,
        favorited = input.favorited,
        type = input.type
    )
}