package com.hi.dhl.wl3d.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.hi.dhl.wl3d.data.entity.FavoriteImageEntity
import com.hi.dhl.wl3d.data.repository.Repository
import com.hi.dhl.wl3d.model.DataItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val dataRepository: Repository
) : ViewModel() {
    val mLoading = ObservableBoolean()

    fun addToFavorite(image: DataItemModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dataRepository.addToFavorite(
                FavoriteImageEntity(
                    accountId = image.accountId,
                    createdAt = image.createdAt,
                    description = image.description,
                    lrThumbnailUrl = image.lrThumbnailUrl,
                    name = image.name,
                    thumbnailUrl = image.thumbnailUrl,
                    updatedAt = image.updatedAt
                )
            )
        }
    }

    suspend fun checkImage(createdAt: Long): Boolean {
        return dataRepository.checkImage(createdAt)
    }

    fun removeFromFavorite(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dataRepository.removeFromFavorite(url)
        }
    }

}