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
        PokemonItemModel(name = input.name, url = input.thumbnailUrl)
}