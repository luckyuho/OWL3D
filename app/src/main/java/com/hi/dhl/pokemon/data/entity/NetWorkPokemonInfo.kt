package com.hi.dhl.pokemon.data.entity

import com.google.gson.annotations.SerializedName

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

data class NetWorkPokemonInfo(
//    @SerializedName("name") val name: String,
//    @SerializedName("height") val height: Int,
//    @SerializedName("weight") val weight: Int,
//    @SerializedName("base_experience") val experience: Int,
//    @SerializedName("types") val types: List<Types>,
//    @SerializedName("stats") val stats: List<Stats>,
//    @SerializedName("sprites") val sprites: Sprites
    @SerializedName("medias") val medias: List<Sprites>,
    @SerializedName("nextToken") val nextToken: String
) {

    data class Sprites(
//        @SerializedName("back_default") val backDefault: String,
//        @SerializedName("back_female") val backFemale: String,
//        @SerializedName("back_shiny") val backShiny: String,
//        @SerializedName("back_shiny_female") val backShinyFemale: String,
//        @SerializedName("front_default") val frontDefault: String,
//        @SerializedName("front_female") val frontfemale: String,
//        @SerializedName("front_shiny") val frontShiny: String,
//        @SerializedName("front_shiny_female") val frontShinyFemale: String
        @SerializedName("accountId") val accountId: String,
        @SerializedName("createdAt") val createdAt: Long,
        @SerializedName("description") val description: String,
        @SerializedName("height") val height: Int,
        @SerializedName("lrThumbnailUrl") val lrThumbnailUrl: String,
//        @SerializedName("lrUrl") val lrUrl: String,
        @SerializedName("mediaId") val mediaId: String,
        @SerializedName("name") val name: String,
        @SerializedName("status") val status: Int,
        @SerializedName("thumbnailUrl") val thumbnailUrl: String,
        @SerializedName("type") val type: Int,
        @SerializedName("updatedAt") val updatedAt: Long,
        @SerializedName("width") val width: Int
    )

//    data class Types(val slot: Int, val type: Type) {
//        data class Type(val name: String, val url: String)
//    }
//
//    data class Stats(@SerializedName("base_stat") val baseStat: Int, val stat: Stat) {
//        data class Stat(val name: String, val url: String)
//    }

//    override fun toString(): String {
//        return "NetWorkPokemonInfo(name='${medias.name}', height=$height, weight=$weight, experience=$experience, types=$types, stats=$stats, sprites=$sprites)"
//    }
}
