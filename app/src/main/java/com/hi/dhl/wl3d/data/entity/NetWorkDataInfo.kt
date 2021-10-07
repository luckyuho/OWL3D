package com.hi.dhl.wl3d.data.entity

import com.google.gson.annotations.SerializedName

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

data class NetWorkDataInfo(
    @SerializedName("medias") val medias: List<Sprites>,
    @SerializedName("nextToken") val nextToken: String
) {
    data class Sprites(
        @SerializedName("accountId") val accountId: String,
        @SerializedName("createdAt") val createdAt: Long,
        @SerializedName("description") val description: String,
        @SerializedName("height") val height: Int,
        @SerializedName("lrUrl") val lrThumbnailUrl: String,
        @SerializedName("mediaId") val mediaId: String,
        @SerializedName("name") val name: String,
        @SerializedName("status") val status: Int,
        @SerializedName("thumbnailUrl") val thumbnailUrl: String,
        @SerializedName("type") val type: Int,
        @SerializedName("updatedAt") val updatedAt: Long,
        @SerializedName("width") val width: Int
    )
}
