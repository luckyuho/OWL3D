package com.hi.dhl.wl3d.ui.browser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import com.hi.dhl.jdatabinding.DataBindingViewHolder
import com.hi.dhl.jdatabinding.dowithTry
import com.hi.dhl.wl3d.R
import com.hi.dhl.wl3d.databinding.RecycleItemDataBinding
import com.hi.dhl.wl3d.model.DataItemModel

class BrowserAdapter(private val listener : OnItemClickListener):
    PagingDataAdapter<DataItemModel, BrowserAdapter.DataViewModel>(DataItemModel.diffCallback) {

    override fun onBindViewHolder(holder: DataViewModel, position: Int) {
        dowithTry {
            val data = getItem(position)
            data?.let {
                holder.bindData(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewModel {
        val view = inflateView(parent, R.layout.recycle_item_data)
        return DataViewModel(view)
    }

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return layoutInflater.inflate(viewType, viewGroup, false)
    }

    interface OnItemClickListener{
        fun onItemClick(image: DataItemModel)
    }

    inner class DataViewModel(view: View) : DataBindingViewHolder<DataItemModel>(view) {
        private val mBinding: RecycleItemDataBinding by viewHolderBinding(view)

        override fun bindData(data: DataItemModel, position: Int) {

            mBinding.root.setOnClickListener {
                listener.onItemClick(data)
            }

            mBinding.apply {
                data.id = "#${position + 1}"
                showdata = data
                executePendingBindings()
            }
        }

    }
}

