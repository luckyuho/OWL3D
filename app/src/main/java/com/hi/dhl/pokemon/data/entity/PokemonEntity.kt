package com.hi.dhl.pokemon.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Entity
data class PokemonEntity(
    @PrimaryKey//(autoGenerate = true)
    val thumbnailUrl: String,
//    val createdAt: Long,
//    var id: Int = 0,
    val name: String,
    val description: String,
//    val thumbnailUrl: String,
    val accountId: String,
    val createdAt: Long,
    val lrThumbnailUrl: String,
    val updatedAt: Long

//    val name: String,
//    var pokemonId: Int = 0,
//    val page: Int = 0,
//    val url: String,
//    val remoteName: String
)