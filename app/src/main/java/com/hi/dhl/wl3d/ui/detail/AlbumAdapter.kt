package com.hi.dhl.wl3d.ui.detail

import android.view.View
import com.hi.dhl.jdatabinding.DataBindingListAdapter
import com.hi.dhl.jdatabinding.DataBindingViewHolder
import com.hi.dhl.wl3d.R
import com.hi.dhl.wl3d.databinding.RecycleItemAlbumBinding
import com.hi.dhl.wl3d.model.PokemonInfoModel

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/22
 *     desc  :
 * </pre>
 */

class AlbumAdapter :
//    DataBindingListAdapter<PokemonInfoModel.AlbumModel>(PokemonInfoModel.AlbumModel.diffCallback) {
    DataBindingListAdapter<PokemonInfoModel>(PokemonInfoModel.diffCallback) {
    override fun layout(position: Int): Int = R.layout.recycle_item_album

    override fun viewHolder(
        layout: Int,
        view: View
//    ): DataBindingViewHolder<PokemonInfoModel.AlbumModel> = AlbumViewHolder(view)
    ): DataBindingViewHolder<PokemonInfoModel> = AlbumViewHolder(view)
}

//class AlbumViewHolder(view: View) : DataBindingViewHolder<PokemonInfoModel.AlbumModel>(view) {
//
//    private val mBinding: RecycleItemAlbumBinding by viewHolderBinding(view)
//
//    override fun bindData(data: PokemonInfoModel.AlbumModel, position: Int) {
//        mBinding.apply {
//            album = data
//            executePendingBindings()
//        }
//    }
//
//}

class AlbumViewHolder(view: View) : DataBindingViewHolder<PokemonInfoModel>(view) {

    private val mBinding: RecycleItemAlbumBinding by viewHolderBinding(view)

    override fun bindData(data: PokemonInfoModel, position: Int) {
        mBinding.apply {
            album = data
            executePendingBindings()
        }
    }

}




//override fun onBindViewHolder(holder: PokemonViewModel, position: Int) {
//    dowithTry {
//        val data = getItem(position)
//        data?.let {
//            holder.bindData(data, position)
//        }
//    }
//}
//
//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewModel {
//    val view = inflateView(parent, R.layout.recycle_item_pokemon)
//    return PokemonViewModel(view)
//}
//
//private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
//    val layoutInflater = LayoutInflater.from(viewGroup.context)
//    return layoutInflater.inflate(viewType, viewGroup, false)
//}
//}
//
//class PokemonViewModel(view: View) : DataBindingViewHolder<PokemonItemModel>(view) {
//    private val mBinding: RecycleItemPokemonBinding by viewHolderBinding(view)
//
//    override fun bindData(data: PokemonItemModel, position: Int) {
//        mBinding.apply {
//            data.id = "#${position + 1}"
//            pokemon = data
//            executePendingBindings()
//        }
//    }
//
//}