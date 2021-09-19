package com.hi.dhl.wl3d.data.entity

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
data class DataEntity(
    @PrimaryKey
    val thumbnailUrl: String,
    val name: String,
    val description: String,
    val accountId: String,
    val createdAt: Long,
    val lrThumbnailUrl: String,
    val updatedAt: Long
)