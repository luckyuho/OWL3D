package com.hi.dhl.wl3d.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hi.dhl.wl3d.AppHelper
import com.hi.dhl.wl3d.data.entity.DataEntity
import com.hi.dhl.wl3d.data.entity.RemoteKeysEntity
import com.hi.dhl.wl3d.data.local.AppDataBase
import com.hi.dhl.wl3d.data.remote.NetworkService
import com.hi.dhl.wl3d.ext.isConnectedNetwork
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@OptIn(ExperimentalPagingApi::class)
class DataRemoteMediator(
    val api: NetworkService,
    val db: AppDataBase
) : RemoteMediator<Int, DataEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DataEntity>
    ): MediatorResult {
        try {

            /**
             * 在这个方法内将会做三件事
             *
             * 1. 参数 LoadType 有个三个值，关于这三个值如何进行判断
             *      LoadType.REFRESH
             *      LoadType.PREPEND
             *      LoadType.APPEND
             *
             * 2. 访问网络数据
             *
             * 3. 将网路插入到本地数据库中
             */

            val dataDao = db.dataDao()
            val remoteKeysDao = db.remoteKeysDao()
            Timber.tag(TAG).e("loadType = ${loadType}")
            // 第一步： 判断 LoadType
            val pageKey = when (loadType) {
                // 首次访问 或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH -> null

                // 在当前加载的数据集的开头加载数据时
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> { // 下来加载更多时触发

                    /**
                     *
                     * 这里主要获取下一页数据的开始位置，可以理解为从什么地方开始加载下一页数据
                     * 这里有两种方式来获取下一页加载数据的位置
                     * 方式一：这种方式比较简单，当前页面最后一条数据是下一页的开始位置
                     * 方式二：比较麻烦，当前分页数据没有对应的远程 key，这个时候需要我们自己建表,
                     */

                    /**
                     * 方式二：比较麻烦，当前分页数据没有对应的远程 key，这个时候需要我们自己建表
                     */
                    val remoteKey = db.withTransaction {
                        db.remoteKeysDao().getRemoteKeys(remoteData)
                    }
                    if (remoteKey == null || remoteKey.nextToken == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey.nextToken
                }
            }

            if (!AppHelper.mContext.isConnectedNetwork()) {
                // 无网络加载本地数据
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            val page = pageKey ?: ""
            val result = api.fetchDataList(page).medias
            Timber.tag(TAG).e(result.toString())

            val endOfPaginationReached = result.isEmpty()

            val item = result.map {
                DataEntity(
                    name = it.name,
                    description = it.description,
                    thumbnailUrl = it.thumbnailUrl,
                    accountId = it.accountId,
                    createdAt = it.createdAt,
                    lrThumbnailUrl = it.lrThumbnailUrl,
                    updatedAt = it.updatedAt
                )
            }

            // 第三步： 插入数据库
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys()
                    dataDao.clearData()
                }

                val nextKey = if (endOfPaginationReached) null else api.fetchDataList(page).nextToken
                val entity = RemoteKeysEntity(
                    remoteName = remoteData,
                    nextToken = nextKey
                )
                remoteKeysDao.insertAll(entity)
                dataDao.insertData(item)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    companion object {
        private val TAG = "DataRemoteMediator"
        private val remoteData = "data"
    }

}