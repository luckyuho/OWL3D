package com.hi.dhl.wl3d.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hi.dhl.wl3d.data.entity.PokemonEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@Dao
interface PokemonDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertPokemon(pokemonList: List<PokemonEntity>)
//
//    @Query("SELECT * FROM PokemonEntity")
//    fun getPokemon(): PagingSource<Int, PokemonEntity>
//
//    @Query("DELETE FROM PokemonEntity where remoteName = :name")
//    suspend fun clearPokemon(name: String)
//
//    @Query("SELECT * FROM PokemonEntity where name LIKE '%' || :parameter || '%'")
//    fun pokemonInfoByParameter(parameter: String): PagingSource<Int, PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM PokemonEntity")
    fun getPokemon(): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM PokemonEntity where thumbnailUrl = :url")
    suspend fun getPokemonInfo(url: String): PokemonEntity

    @Query("DELETE FROM PokemonEntity")
    suspend fun clearPokemon()

    @Query("SELECT * FROM PokemonEntity where name LIKE '%' || :parameter || '%'")
    fun pokemonInfoByParameter(parameter: String): PagingSource<Int, PokemonEntity>
}