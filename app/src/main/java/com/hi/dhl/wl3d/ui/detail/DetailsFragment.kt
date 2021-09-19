package com.hi.dhl.wl3d.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hi.dhl.jdatabinding.DataBindingFragment
import com.hi.dhl.wl3d.R
import com.hi.dhl.wl3d.databinding.FragmentDetailsBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isChecked = false
        CoroutineScope(Dispatchers.Main).launch {
            isChecked = mViewModel.checkImage(args.image.createdAt)
            withContext(Dispatchers.Main) {
                if (isChecked) {
                    mBinding.toggleFavorite.isChecked = true
                    isChecked = true
                } else {
                    mBinding.toggleFavorite.isChecked = false
                    isChecked = false
                }
            }
        }

        mBinding.apply {
            dataListModel = args.image
        }


        mBinding.toggleFavorite.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                mViewModel.addToFavorite(args.image)
            } else {
                mViewModel.removeFromFavorite(args.image.thumbnailUrl)
            }
            mBinding.toggleFavorite.isChecked = isChecked
        }

    }

}