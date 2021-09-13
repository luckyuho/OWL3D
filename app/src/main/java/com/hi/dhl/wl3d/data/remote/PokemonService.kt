package com.hi.dhl.wl3d.data.remote

import com.hi.dhl.wl3d.data.entity.NetWorkPokemonInfo
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
interface PokemonService {
//    @GET("pokemon")
//    suspend fun fetchPokemonList(
//        @Query("limit") limit: Int = 20,
//        @Query("offset") offset: Int = 0
//    ): ListingResponse
//
//    @GET("pokemon/{name}")
//    suspend fun fetchPokemonInfo(@Path("name") name: String): NetWorkPokemonInfo

    @GET("/dev/media")
    suspend fun fetchPokemonList(@Query("startToken") nextToken: String): NetWorkPokemonInfo

//    @GET("/dev/media")
//    suspend fun getNextKey(@Query("startToken") nextToken: String): RemoteKeysEntity
}