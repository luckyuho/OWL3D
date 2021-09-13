package com.hi.dhl.wl3d.data.local

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.hi.dhl.wl3d.data.entity.PokemonInfoEntity
import com.hi.dhl.wl3d.ext.fromJson
import com.hi.dhl.wl3d.ext.typedToJson

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/21
 *     desc  :
 * </pre>
 */
open class LocalTypeConverter {

//    @TypeConverter
//    fun json2StatsEntity(src: String): List<PokemonInfoEntity.Stats>? =
//        GsonBuilder().create().fromJson(src)
//
//    @TypeConverter
//    fun statsEntity2Json(data: List<PokemonInfoEntity.Stats>): String =
//        GsonBuilder().create().typedToJson(data)
//
//    @TypeConverter
//    fun json2TypeEntity(src: String): List<PokemonInfoEntity.Type>? =
//        GsonBuilder().create().fromJson(src)
//
//    @TypeConverter
//    fun typeEntity2Json(data: List<PokemonInfoEntity.Type>): String =
//        GsonBuilder().create().typedToJson(data)

    @TypeConverter
    fun json2StatsEntity(src: String): List<PokemonInfoEntity> =
        GsonBuilder().create().fromJson(src)

    @TypeConverter
    fun statsEntity2Json(data: List<PokemonInfoEntity>): String =
        GsonBuilder().create().typedToJson(data)

    @TypeConverter
    fun json2TypeEntity(src: String): List<PokemonInfoEntity> =
        GsonBuilder().create().fromJson(src)

    @TypeConverter
    fun typeEntity2Json(data: List<PokemonInfoEntity>): String =
        GsonBuilder().create().typedToJson(data)

}