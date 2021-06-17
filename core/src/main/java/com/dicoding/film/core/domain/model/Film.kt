package com.dicoding.film.core.domain.model

import android.os.Parcelable
import com.dicoding.film.core.data.source.remote.response.GenreResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    var id: Int,
    var title: String,
    var genre:  ArrayList<GenreResponse>,
    var overview: String,
    var userScore: Double,
    var releaseYear: String,
    var duration:Int,
    var photo:String,
    var favorited: Boolean = false,
    var type: String
) : Parcelable