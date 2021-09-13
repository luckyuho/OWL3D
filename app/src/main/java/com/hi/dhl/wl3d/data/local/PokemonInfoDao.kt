package com.hi.dhl.wl3d.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hi.dhl.wl3d.data.entity.PokemonInfoEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Dao
interface PokemonInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonInfoEntity: PokemonInfoEntity)

    @Query("SELECT * FROM PokemonInfoEntity where name = :name")
    suspend fun getPokemon(name: String): PokemonInfoEntity

//    @Query("DELETE FROM PokemonInfoEntity where id = :id")
//    suspend fun clearPokemonInfo(id: Int)
}