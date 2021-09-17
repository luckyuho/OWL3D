package com.hi.dhl.wl3d.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity
data class FavoriteImageEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val thumbnailUrl: String,
//    val name: String,
//    val height: Int,
//    val weight: Int,
//    val experience: Int,
//    val types: List<Type>,
//    val stats: List<Stats>,
//    @Embedded val sprites: Sprites
    val accountId: String,
    val createdAt: Long,
    val description: String,
//    val height: Int,
    val lrThumbnailUrl: String,
//    val lrUrl: String,
//    val mediaId: String,
    val name: String,
//    val status: Int,

//    val type: Int,
    val updatedAt: Long
//    val width: Int
)