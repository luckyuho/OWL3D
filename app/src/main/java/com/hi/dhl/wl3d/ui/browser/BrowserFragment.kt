package com.hi.dhl.wl3d.ui.Browser
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.hi.dhl.jdatabinding.DataBindingFragment
import com.hi.dhl.jdatabinding.binding
import com.hi.dhl.wl3d.R
import com.hi.dhl.wl3d.databinding.ActivityMainBinding
import com.hi.dhl.wl3d.databinding.FragmentDetailsBinding
import com.hi.dhl.wl3d.model.PokemonItemModel
//import com.hi.dhl.wl3d.ui.detail.AlbumAdapter
import com.hi.dhl.wl3d.ui.detail.DetailActivity
import com.hi.dhl.wl3d.ui.detail.DetailViewModel
import com.hi.dhl.wl3d.ui.detail.DetailsFragment
import com.hi.dhl.wl3d.ui.main.MainViewModel
import com.hi.dhl.wl3d.ui.main.PokemonAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import com.hi.dhl.wl3d.databinding.FragmentBrowserBinding
import com.hi.dhl.wl3d.ui.browser.BrowserAdapter
import com.hi.dhl.wl3d.ui.browser.BrowserViewModel
import com.hi.dhl.wl3d.ui.main.footer.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class BrowserFragment : DataBindingFragment(R.layout.fragment_browser), BrowserAdapter.OnItemClickListener{

//    private val mBinding: FragmentDetailsBinding by binding()
//    private val mViewModel: DetailViewModel by activityViewModels()
//    private lateinit var mPokemonModel: PokemonItemModel
//    //    private lateinit var mPokemonInfoModel: PokemonInfoModel
//    val mAlbumAdapter: AlbumAdapter by lazy { AlbumAdapter() }

    private var _mBinding: FragmentBrowserBinding ?= null //by binding()
    private val mBinding get() = _mBinding!!
//    private val mViewModel: MainViewModel by viewModels()
    private val mViewModel: BrowserViewModel by viewModels()
//    private val mPokemonAdapter by lazy { BrowserAdapter() }
//
    private val adapter by lazy { BrowserAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    _mBinding = FragmentBrowserBinding.bind(view)

//        val adapter = BrowserAdapter(this)

        mBinding.apply {
//            recyleView.adapter = adapter.withLoadStateHeaderAndFooter(
            recyleView.adapter = adapter.withLoadStateFooter(
//                header = FooterAdapter(adapter),
                footer = FooterAdapter(adapter)
            )
            mainViewModel = mViewModel
            lifecycleOwner = this@BrowserFragment
        }

//        /**
//         * 分为 数据库 和 网络搜索
//         * 可以运行注释掉的代码，文章链接：https://juejin.cn/post/6854573220457086990
//         */
//        mBinding.layoutHeader.searchView.addTextChangedListener {
//            val result = it.toString()
//            mViewModel.queryParameterForDb(result) // 搜索数据库
////                mViewModel.queryParameterForNetWork(result) // 网络搜索
//        }

        mViewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
            mBinding.swiperRefresh.isEnabled = false
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { state ->
                mBinding.swiperRefresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }



//        // 数据库搜索回调监听
//        mViewModel.searchResultForDb.observe(viewLifecycleOwner, Observer {
//            mPokemonAdapter.submitData(lifecycle, it)
//        })
//
//        // 网络搜索回调监听
//        mViewModel.searchResultMockNetWork.observe(viewLifecycleOwner, Observer {
////            mPokemonAdapter.submitData(lifecycle, it)
//        })
    }


    override fun onItemClick(movie: PokemonItemModel) {
        val action = BrowserFragmentDirections.actionNavMovieToNavDetails(movie)
        findNavController().navigate(action)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
//
//        mPokemonModel = requireNotNull(arguments?.getParcelable(KEY_LIST_MODEL)) {
//            "params is not null"
//        }
//
////        PokemonInfoModel = PokemonInfoModel()
//
//        mBinding.apply {
////            pokemonListModel = mPokemonModel
////            pokemonListModel = PokemonInfoModel()
//            albumAdapter = mAlbumAdapter
//            viewModel = mViewModel.apply {
//                fectchPokemonInfo2(mPokemonModel.url)
//                    .observe(viewLifecycleOwner, Observer {})
//            }
////            Log.d("bindingInfoinFragment", viewModel.pokemon.value?.lrThumbnailUrl.toString())
//
//            lifecycleOwner = this@DetailsFragment
//        }
//
//        mViewModel.failure.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//        })
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
//    }
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
//}
}