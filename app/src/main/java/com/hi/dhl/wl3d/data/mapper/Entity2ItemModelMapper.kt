package com.hi.dhl.wl3d.data.mapper

import com.hi.dhl.wl3d.data.entity.PokemonEntity
import com.hi.dhl.wl3d.model.PokemonItemModel

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
class Entity2ItemModelMapper : Mapper<PokemonEntity, PokemonItemModel> {

//    override fun map(input: PokemonEntity): PokemonItemModel =
//        PokemonItemModel(name = input.name, url = input.url)

    override fun map(input: PokemonEntity): PokemonItemModel =
        PokemonItemModel(
            name = input.name,
            accountId = input.accountId,
            createdAt = input.createdAt,
            description = input.description,
            lrThumbnailUrl = input.lrThumbnailUrl,
            thumbnailUrl = input.thumbnailUrl,
            updatedAt = input.updatedAt)
//        PokemonItemModel(name = input.name, thumbnailUrl = input.thumbnailUrl)

}