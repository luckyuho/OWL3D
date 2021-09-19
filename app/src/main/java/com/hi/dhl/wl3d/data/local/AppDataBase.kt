package com.hi.dhl.wl3d.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hi.dhl.wl3d.data.entity.FavoriteImageEntity
import com.hi.dhl.wl3d.data.entity.DataEntity
import com.hi.dhl.wl3d.data.entity.RemoteKeysEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Database(
    entities = arrayOf(DataEntity::class, RemoteKeysEntity::class, FavoriteImageEntity::class),
    version = 1, exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun dataDao(): DataDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun favoriteImageDao(): FavoriteImageDao

}
