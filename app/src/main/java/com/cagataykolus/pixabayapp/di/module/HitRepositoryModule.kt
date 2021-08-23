package com.cagataykolus.pixabayapp.di.module

import com.cagataykolus.pixabayapp.data.repository.DefaultHitRepository
import com.cagataykolus.pixabayapp.data.repository.HitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class HitRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindHitRepository(repository: DefaultHitRepository): HitRepository
}