package com.dicoding.film.core.data.repository

import com.dicoding.film.core.data.NetworkBoundResource
import com.dicoding.film.core.data.source.local.LocalDataSource
import com.dicoding.film.core.domain.repository.IFilmRepository
import com.dicoding.film.core.data.source.local.entity.FilmEntity
import com.dicoding.film.core.data.source.remote.RemoteDataSource
import com.dicoding.film.core.data.source.remote.api.ApiResponse
import com.dicoding.film.core.domain.model.Film
import com.dicoding.film.core.utils.AppExecutors
import com.dicoding.film.core.utils.DataMapper
import com.dicoding.film.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmRepository(private val remoteDataSource: RemoteDataSource,
                     private val localDataSource: LocalDataSource,
                     private val appExecutors: AppExecutors
) :
    IFilmRepository {

    override fun getAllFilm(): Flow<Resource<List<Film>>> {
        return object : NetworkBoundResource<List<Film>, List<FilmEntity>>() {
            public override fun loadFromDB(): Flow<List<Film>>
                {
                    return localDataSource.getAllFilm().map { DataMapper.mapEntitiesToDomain(it) }
                }

            override fun shouldFetch(data: List<Film>?): Boolean = data == null || data.isEmpty()

            public override suspend fun createCall(): Flow<ApiResponse<List<FilmEntity>>> =
                remoteDataSource.getAllFilm()

            override suspend  fun saveCallResult(data: List<FilmEntity>) {
                localDataSource.insertFilm(data)
            }

        }.asFlow()

    }

    override fun getAllTvShow(): Flow<Resource<List<Film>>> {
        return object : NetworkBoundResource<List<Film>, List<FilmEntity>>() {
            public override fun loadFromDB(): Flow<List<Film>>
            {
                return localDataSource.getAllTvShow().map { DataMapper.mapEntitiesToDomain(it) }
            }


            override fun shouldFetch(data: List<Film>?): Boolean =
                data == null || data.isEmpty()

            public override suspend fun createCall(): Flow<ApiResponse<List<FilmEntity>>> =
                remoteDataSource.getAllTvShow()

            override suspend fun saveCallResult(data: List<FilmEntity>) {
                localDataSource.insertFilm(data)
            }

        }.asFlow()
    }



    override fun getDetailFilm(id: Int): Flow<Resource<Film>> {
        return object : NetworkBoundResource<Film, FilmEntity>() {
            public override fun loadFromDB(): Flow<Film> =
                localDataSource.getDetailFilm(id).map { DataMapper.convertEntityToDomain(it) }
            override fun shouldFetch(data: Film?): Boolean =
                data?.genre?.size==0

            public override suspend fun createCall(): Flow<ApiResponse<FilmEntity>> =
                remoteDataSource.getDetailFilm(id)

            override suspend fun saveCallResult(data: FilmEntity) {
                appExecutors.diskIO().execute { localDataSource.updateFilm(data)}
            }


        }.asFlow()
    }

    override fun getDetailTv(id: Int):Flow<Resource<Film>> {
        return object : NetworkBoundResource<Film, FilmEntity>() {
            public override fun loadFromDB(): Flow<Film> =
                localDataSource.getDetailFilm(id).map { DataMapper.convertEntityToDomain(it) }
            override fun shouldFetch(data: Film?): Boolean =
                data?.genre?.size==0

            public override suspend fun createCall(): Flow<ApiResponse<FilmEntity>> =
                remoteDataSource.getDetailTv(id)

            override suspend fun saveCallResult(data: FilmEntity) {
                appExecutors.diskIO().execute { localDataSource.updateFilm(data)}
            }


        }.asFlow()
    }

    override fun getFavoritedFilm(): Flow<List<Film>> {

        return localDataSource.getFavoritedFilm().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun getFavoritedTvShow(): Flow<List<Film>> {
        return localDataSource.getFavoritedTvShow().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteFilm(film: Film, state: Boolean){
        val filmEntity = DataMapper.mapDomainToEntity(film)
        appExecutors.diskIO().execute { localDataSource.setFilmFavorite(filmEntity,state)}
    }
    override fun setFavoriteTvShow(tv: Film, state: Boolean){
        val tvEntity = DataMapper.mapDomainToEntity(tv)
        appExecutors.diskIO().execute { localDataSource.setFilmFavorite(tvEntity,state)}
    }
}