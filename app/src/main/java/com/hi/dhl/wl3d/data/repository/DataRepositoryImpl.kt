package com.hi.dhl.wl3d.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hi.dhl.wl3d.data.entity.FavoriteImageEntity
import com.hi.dhl.wl3d.data.entity.DataEntity
import com.hi.dhl.wl3d.data.local.AppDataBase
import com.hi.dhl.wl3d.data.mapper.Mapper
import com.hi.dhl.wl3d.data.remote.NetworkService
import com.hi.dhl.wl3d.model.DataItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

class DataRepositoryImpl(
    val api: NetworkService,
    val db: AppDataBase,
    val pageConfig: PagingConfig,
    val mapper2ItemMolde: Mapper<DataEntity, DataItemModel>,
    val mapperfromfavorite: Mapper<FavoriteImageEntity, DataItemModel>
) : Repository {

    override fun fetchDataList(): Flow<PagingData<DataItemModel>> {
        return Pager(
            config = pageConfig,
            remoteMediator = DataRemoteMediator(api, db)
        ) {
            db.dataDao().getData()
        }.flow.map { pagingData ->
            pagingData.map { mapper2ItemMolde.map(it) }
        }
    }

    override suspend fun fetchDataByParameter(parameter: String): Flow<PagingData<DataItemModel>> {
        return Pager(pageConfig) {
            // 加载数据库的数据
            db.favoriteImageDao().imageInfoByParameter(parameter)
        }.flow.map { pagingData ->

            // 数据映射，数据库实体 PersonEntity ——>  上层用到的实体 Person
            pagingData.map { mapperfromfavorite.map(it) }
        }
    }


    // Favorite
    override suspend fun addToFavorite(favoriteImage: FavoriteImageEntity) = db.favoriteImageDao().addToFavorite(favoriteImage)

    override fun getFavoriteImage() : Flow<PagingData<DataItemModel>> {
        return Pager(pageConfig) {
            // 加载数据库的数据
            db.favoriteImageDao().getFavoriteImage()
        }.flow.map { pagingData ->

            // 数据映射，数据库实体 PersonEntity ——>  上层用到的实体 Person
            pagingData.map { mapperfromfavorite.map(it) }
        }

    }

    override suspend fun checkImage(createdAt: Long): Boolean {
        val favoriteImageDao = db.favoriteImageDao()
        return favoriteImageDao.checkImage(createdAt) > 0
    }

    override suspend fun removeFromFavorite(id: String){
        db.favoriteImageDao().removeFromFavorite(id)
    }

}