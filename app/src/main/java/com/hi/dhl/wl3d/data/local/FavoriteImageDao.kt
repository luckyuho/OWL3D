package com.hi.dhl.wl3d.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hi.dhl.wl3d.data.entity.FavoriteImageEntity
import com.hi.dhl.wl3d.data.entity.PokemonEntity

@Dao
interface FavoriteImageDao {
    @Insert
    suspend fun addToFavorite(favoriteMovie: FavoriteImageEntity)

    @Query("SELECT * FROM FavoriteImageEntity")
    fun getFavoriteMovie(): PagingSource<Int, FavoriteImageEntity>//: LiveData<List<FavoriteImageEntity>>

    @Query("SELECT count(*) FROM FavoriteImageEntity WHERE FavoriteImageEntity.thumbnailUrl = :url")
    fun checkMovie(url: String): Int

    @Query("DELETE FROM FavoriteImageEntity WHERE FavoriteImageEntity.thumbnailUrl = :url" )
    suspend fun removeFromFavorite(url: String) : Int
}