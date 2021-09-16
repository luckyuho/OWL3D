package com.hi.dhl.wl3d.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hi.dhl.wl3d.data.entity.FavoriteImageEntity
import com.hi.dhl.wl3d.data.entity.PokemonEntity
import com.hi.dhl.wl3d.data.entity.PokemonInfoEntity
import com.hi.dhl.wl3d.data.entity.RemoteKeysEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Database(
    entities = arrayOf(PokemonEntity::class, RemoteKeysEntity::class, PokemonInfoEntity::class, FavoriteImageEntity::class),
    version = 1, exportSchema = false
)
//@TypeConverters(value = arrayOf(LocalTypeConverter::class))
abstract class AppDataBase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun pokemonInfoDao(): PokemonInfoDao
    abstract fun favoriteImageDao(): FavoriteImageDao

}
