package com.hi.dhl.wl3d.ui.Browser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.hi.dhl.jdatabinding.DataBindingFragment
import com.hi.dhl.wl3d.R
import com.hi.dhl.wl3d.model.DataItemModel
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
class BrowserFragment : DataBindingFragment(R.layout.fragment_browser),
    BrowserAdapter.OnItemClickListener {

    private var _mBinding: FragmentBrowserBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: BrowserViewModel by viewModels()
    private val adapter by lazy { BrowserAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _mBinding = FragmentBrowserBinding.bind(view)


        mBinding.apply {
            recyleView.adapter = adapter.withLoadStateFooter(
                footer = FooterAdapter(adapter)
            )
            mainViewModel = mViewModel
            lifecycleOwner = this@BrowserFragment
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

    }

    override fun onItemClick(image: DataItemModel) {
        val action = BrowserFragmentDirections.actionNavImageToNavDetails(image)
        findNavController().navigate(action)
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}