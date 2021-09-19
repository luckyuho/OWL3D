package com.hi.dhl.wl3d.ui.browser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hi.dhl.wl3d.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class BrowserViewModel @Inject constructor(
    dataRepository: Repository
) : ViewModel() {

    val image = dataRepository.fetchDataList().cachedIn(viewModelScope).asLiveData()

}