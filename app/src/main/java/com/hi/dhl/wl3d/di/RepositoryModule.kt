package com.hi.dhl.wl3d.di

import com.hi.dhl.wl3d.data.DataFactory
import com.hi.dhl.wl3d.data.local.AppDataBase
import com.hi.dhl.wl3d.data.remote.NetworkService
import com.hi.dhl.wl3d.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(
        api: NetworkService,
        db: AppDataBase
    ): Repository {
        return DataFactory.makeDataRepository(api, db)
    }

}