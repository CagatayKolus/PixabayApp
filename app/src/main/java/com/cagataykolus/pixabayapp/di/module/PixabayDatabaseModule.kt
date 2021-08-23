package com.cagataykolus.pixabayapp.di.module

import android.app.Application
import com.cagataykolus.pixabayapp.data.local.HitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class PixabayDatabaseModule {
    @Singleton
    @Provides
    fun provideHitDatabase(application: Application) = HitDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideHitDao(database: HitDatabase) = database.getHitDao()
}