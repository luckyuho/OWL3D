package com.hi.dhl.wl3d.di

import com.hi.dhl.wl3d.data.PokemonFactory
import com.hi.dhl.wl3d.data.local.AppDataBase
import com.hi.dhl.wl3d.data.remote.PokemonService
import com.hi.dhl.wl3d.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(
        api: PokemonService,
        db: AppDataBase
    ): Repository {
        return PokemonFactory.makePokemonRepository(api, db)
    }

}