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
data class RemoteKeysEntity(
    @PrimaryKey
    val remoteName: String,
    val nextToken: String? = ""
)
