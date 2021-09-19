package com.hi.dhl.wl3d.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.hi.dhl.jdatabinding.DataBindingFragment
import com.hi.dhl.wl3d.R
import com.hi.dhl.wl3d.databinding.FragmentFavoriteBinding
import com.hi.dhl.wl3d.model.DataItemModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FavoriteFragment : DataBindingFragment(R.layout.fragment_favorite),
    FavoriteAdapter.OnItemClickListener {

    private var _mBinding: FragmentFavoriteBinding? = null //by binding()
    private val mBinding get() = _mBinding!!
    private val mViewModel: FavoriteViewModel by viewModels()
    private val adapter by lazy { FavoriteAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _mBinding = FragmentFavoriteBinding.bind(view)

        mBinding.apply {
            recyleView.adapter = adapter.withLoadStateFooter(FavoriteFooterAdapter(adapter))
            favoriteViewModel = mViewModel
            lifecycleOwner = this@FavoriteFragment
        }

        mViewModel.image.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
            mBinding.swiperRefresh.isEnabled = false
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { state ->
                mBinding.swiperRefresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }

        // 数据库搜索回调监听
        mBinding.layoutHeader.searchView.addTextChangedListener {
            val result = it.toString()
            mViewModel.queryParameterForDb(result) // 搜索数据库
        }

        // 数据库搜索回调监听
        mViewModel.searchResultForDb.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })

    }

    override fun onItemClick(image: DataItemModel) {
        val action = FavoriteFragmentDirections.actionNavFavoriteToNavDetails(image)
        findNavController().navigate(action)
    }

}

