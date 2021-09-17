package com.hi.dhl.wl3d.ui.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.hi.dhl.jdatabinding.DataBindingFragment
import com.hi.dhl.wl3d.R
import com.hi.dhl.wl3d.databinding.FragmentDetailsBinding
import com.hi.dhl.wl3d.model.PokemonItemModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/22
 *     desc  : 演示的是使用带参数的 Fragment
 * </pre>
 */

// 如果使用带参数的 Fragment 需要设置 FragmentFactory，告诉系统如何实例化 Fragment
@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailsFragment : DataBindingFragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()
    private val mBinding: FragmentDetailsBinding by binding()
    private val mViewModel: DetailViewModel by viewModels()
//    private lateinit var mPokemonModel: PokemonItemModel
////    private lateinit var mPokemonInfoModel: PokemonInfoModel
////    val mAlbumAdapter: AlbumAdapter by lazy { AlbumAdapter() }
//
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        mPokemonModel = requireNotNull(arguments?.getParcelable(KEY_LIST_MODEL)) {
//            "params is not null"
//        }
//
////        PokemonInfoModel = PokemonInfoModel()
//

    var isChecked = false
    CoroutineScope(Dispatchers.Main).launch{
        isChecked = mViewModel.checkMovie(args.movie.createdAt)
        Log.d("showfavorite1", isChecked.toString())
        withContext(Dispatchers.Main){
            if (isChecked){
                mBinding.toggleFavorite.isChecked = true
                isChecked = true
            }else{
                mBinding.toggleFavorite.isChecked = false
                isChecked = false
            }
        }
//        Log.d("showfavorite", boolean.toString())
//        withContext(Dispatchers.Main){
//            _isChecked = boolean
//        }
    }
    Log.d("showfavorite", isChecked.toString())

        mBinding.apply {
            pokemonListModel = args.movie
//            isCheck = isChecked
////            pokemonListModel = PokemonInfoModel()
////            albumAdapter = mAlbumAdapter
//            viewModel = mViewModel.apply {
//                fectchPokemonInfo2(args.movie.thumbnailUrl)
//                    .observe(viewLifecycleOwner, Observer {})
//            }
////            Log.d("bindingInfoinFragment", viewModel.pokemon.value?.lrThumbnailUrl.toString())
//
//            lifecycleOwner = this@DetailsFragment
        }

//    Log.d("showargslrThumbnailUrl", args.movie.lrThumbnailUrl)
//    Log.d("showargsthumbnailUrl", args.movie.thumbnailUrl)
//

//        mViewModel.failure.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//        })


    mBinding.toggleFavorite.setOnClickListener {
        isChecked = !isChecked
        if (isChecked){
            mViewModel.addToFavorite(args.movie)
        } else{
            mViewModel.removeFromFavorite(args.movie.thumbnailUrl)
        }
        mBinding.toggleFavorite.isChecked = isChecked
//        mViewModel.addToFavorite(args.movie)
//        Toast.makeText(this.context, "${mBinding.toggleFavorite.isChecked}", Toast.LENGTH_LONG).show()
    }


//    toggleFavorite.setOnClickListener {
//        _isChecked = !_isChecked
//        if (_isChecked){
//            viewModel.addToFavorite(movie)
//        } else{
//            viewModel.removeFromFavorite(movie.id)
//        }
//        toggleFavorite.isChecked = _isChecked
//    }

//        fun toggleFavorite(_isChecked: Boolean) {
//            if (_isChecked) {
//                mViewModel.addToFavorite(args.movie)
//            }
////        } else{
////            mViewModel.removeFromFavorite(movie.id)
////        }
//        }


//
//        /**
//         * 这三种方式的使用和 之前在 [DetailActivity] 使用方式一样
//         */
//
////        // 方法一
////        mViewModel.pokemon.observe(this, Observer {
////            // 将数据显示在页面上
////        })
////
////        // 方法二
////        mViewModel.fectchPokemonInfo2(mPokemonModel.name).observe(this, Observer {
////            // 将数据显示在页面上
////        })
////
////        // 方法三
////        lifecycleScope.launch {
////            mViewModel.apply {
////                fectchPokemonInfo3(mPokemonModel.name).observe(viewLifecycleOwner, Observer {
////                    // 将数据显示在页面上
////                })
////            }
////        }
    }
//
//    companion object {
//        private val KEY_LIST_MODEL = "listModel"
//
//        fun addFragment(
//            manager: FragmentManager,
//            params: PokemonItemModel,
//            fragmentContainerId: Int
//        ) {
//
//            manager.commit {
//                val bundle = bundleOf(KEY_LIST_MODEL to params)
//                replace(fragmentContainerId, DetailsFragment::class.java, bundle)
//            }
//        }
//    }
//    companion object{
//            fun addFavorite(
//            context: Context,
//        ) {
//
//            }
//        }
//    }
}