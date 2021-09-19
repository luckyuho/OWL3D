package com.hi.dhl.wl3d.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hi.dhl.wl3d.data.entity.FavoriteImageEntity

@Dao
interface FavoriteImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(favoriteImage: FavoriteImageEntity)

    @Query("SELECT * FROM FavoriteImageEntity")
    fun getFavoriteImage(): PagingSource<Int, FavoriteImageEntity>

    @Query("SELECT count(*) FROM FavoriteImageEntity WHERE FavoriteImageEntity.createdAt = :createdAt")
    fun checkImage(createdAt: Long): Int

    @Query("DELETE FROM FavoriteImageEntity WHERE FavoriteImageEntity.thumbnailUrl = :url" )
    suspend fun removeFromFavorite(url: String) : Int

    @Query("SELECT * FROM FavoriteImageEntity where name LIKE '%' || :parameter || '%'")
    fun imageInfoByParameter(parameter: String): PagingSource<Int, FavoriteImageEntity>
}