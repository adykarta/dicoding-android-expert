package com.dicoding.film

import android.app.Application
import com.dicoding.film.core.di.databaseModule
import com.dicoding.film.core.di.networkModule
import com.dicoding.film.core.di.repositoryModule
import com.dicoding.film.di.useCaseModule
import com.dicoding.film.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(org.koin.core.logger.Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}