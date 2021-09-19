package com.hi.dhl.wl3d.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteImageEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val thumbnailUrl: String,
    val accountId: String,
    val createdAt: Long,
    val description: String,
    val lrThumbnailUrl: String,
    val name: String,
    val updatedAt: Long
)