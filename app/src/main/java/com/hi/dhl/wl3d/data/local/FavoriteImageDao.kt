package com.hi.dhl.wl3d.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hi.dhl.wl3d.data.entity.FavoriteImageEntity
import com.hi.dhl.wl3d.data.entity.PokemonEntity
import com.hi.dhl.wl3d.model.PokemonItemModel

@Dao
interface FavoriteImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(favoriteMovie: FavoriteImageEntity)

    @Query("SELECT * FROM FavoriteImageEntity")
    fun getFavoriteMovie(): PagingSource<Int, FavoriteImageEntity>//: LiveData<List<FavoriteImageEntity>>

    @Query("SELECT count(*) FROM FavoriteImageEntity WHERE FavoriteImageEntity.createdAt = :createdAt")
    fun checkMovie(createdAt: Long): Int

    @Query("DELETE FROM FavoriteImageEntity WHERE FavoriteImageEntity.thumbnailUrl = :url" )
    suspend fun removeFromFavorite(url: String) : Int

//    @Query("SELECT * FROM PokemonEntity where name LIKE '%' || :parameter || '%'")
//    fun pokemonInfoByParameter(parameter: String): PagingSource<Int, FavoriteImageEntity>
}