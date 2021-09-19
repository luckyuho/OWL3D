package com.hi.dhl.wl3d.data.repository

import androidx.paging.PagingData
import com.hi.dhl.wl3d.data.entity.FavoriteImageEntity
import com.hi.dhl.wl3d.model.DataItemModel
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
interface Repository {
    fun fetchDataList(): Flow<PagingData<DataItemModel>>
    suspend fun fetchDataByParameter(parameter: String): Flow<PagingData<DataItemModel>>

    // Favorite
    suspend fun addToFavorite(favoriteImage: FavoriteImageEntity) : Unit
    fun getFavoriteImage() : Flow<PagingData<DataItemModel>>
    suspend fun checkImage(createdAt: Long) : Boolean
    suspend fun removeFromFavorite(id: String) : Unit
}
