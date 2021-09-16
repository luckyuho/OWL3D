package com.hi.dhl.wl3d.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Parcelize
data class PokemonItemModel(
    var id: String = "",
    val name: String,
    val accountId: String = "",
    val createdAt: Long = 0,
    val description: String = "",
    val lrThumbnailUrl: String = "",
    val thumbnailUrl: String,
    val updatedAt: Long = 0
) : Parcelable {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<PokemonItemModel>() {
            override fun areItemsTheSame(
                oldItem: PokemonItemModel,
                newItem: PokemonItemModel
            ): Boolean =
                oldItem.thumbnailUrl == newItem.thumbnailUrl

            override fun areContentsTheSame(
                oldItem: PokemonItemModel,
                newItem: PokemonItemModel
            ): Boolean =
                oldItem == newItem
        }
    }
}