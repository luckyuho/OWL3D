package com.hi.dhl.wl3d.data

import androidx.paging.PagingConfig
import com.hi.dhl.wl3d.data.local.AppDataBase
import com.hi.dhl.wl3d.data.mapper.Entity2ItemModelMapper
import com.hi.dhl.wl3d.data.mapper.InfoEntity2InfoModelMapper
import com.hi.dhl.wl3d.data.remote.NetworkService
import com.hi.dhl.wl3d.data.repository.DataRepositoryImpl
import com.hi.dhl.wl3d.data.repository.Repository

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
object DataFactory {

    fun makeDataRepository(api: NetworkService, db: AppDataBase): Repository =
        DataRepositoryImpl(
            api,
            db,
            pagingConfig,
            Entity2ItemModelMapper(),
            InfoEntity2InfoModelMapper()
        )

    val pagingConfig = PagingConfig(
        // 每页显示的数据的大小
        pageSize = 40,

        // 开启占位符
        enablePlaceholders = true,

        // 预刷新的距离，距离最后一个 item 多远时加载数据
        // 默认为 pageSize
        prefetchDistance = 4,

        /**
         * 初始化加载数量，默认为 pageSize * 3
         *
         * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
         * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
         */
        initialLoadSize = 40
    )
}