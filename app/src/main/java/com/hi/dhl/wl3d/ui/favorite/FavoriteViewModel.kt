package com.hi.dhl.wl3d.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hi.dhl.wl3d.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: Repository
) : ViewModel() {

    private val mChanncel = ConflatedBroadcastChannel<String>()
    val image = favoriteRepository.getFavoriteImage().cachedIn(viewModelScope).asLiveData()

    // 使用 ConflatedBroadcastChannel 进行搜索

    val searchResultForDb = mChanncel.asFlow()
        // 避免在单位时间内，快输入造成大量的请求
        .debounce(500)
        //  避免重复的搜索请求。假设正在搜索 dhl，用户删除了 l  然后输入 l。最后的结果还是 dhl。它就不会再执行搜索查询 dhl
        // distinctUntilChanged 对于 StateFlow 任何实例是没有效果的
        .distinctUntilChanged()
        .flatMapLatest { search -> // 只显示最后一次搜索的结果，忽略之前的请求
            favoriteRepository.fetchDataByParameter(search).cachedIn(viewModelScope)
        }
        .catch { throwable ->
            //  异常捕获
        }.asLiveData()

    // 根据关键词搜索
    fun queryParameterForDb(parameter: String) = mChanncel.offer(parameter)
}