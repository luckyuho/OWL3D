package com.hi.dhl.wl3d.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.hi.dhl.wl3d.data.entity.FavoriteImageEntity
import com.hi.dhl.wl3d.data.remote.doFailure
import com.hi.dhl.wl3d.data.remote.doSuccess
import com.hi.dhl.wl3d.data.repository.Repository
import com.hi.dhl.wl3d.model.PokemonInfoModel
import com.hi.dhl.wl3d.model.PokemonItemModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@FlowPreview
@ExperimentalCoroutinesApi
class DetailViewModel @ViewModelInject constructor(
    private val pokemonRepository: Repository
) : ViewModel() {
    val mLoading = ObservableBoolean()

    // 私有的 MutableLiveData 可变的，对内访问
    private val _pokemon = MutableLiveData<PokemonInfoModel>()

    // 对外暴露不可变的 LiveData，只能查询
    val pokemon: LiveData<PokemonInfoModel> = _pokemon

    private val _failure = MutableLiveData<String>()
    val failure = _failure

    /**
     * 方法二
     */
    fun fectchPokemonInfo2(url: String) = liveData<PokemonInfoModel> {
        pokemonRepository.fetchPokemonInfo(url)
            .onStart {
                // 在调用 flow 请求数据之前，做一些准备工作，例如显示正在加载数据的按钮
                mLoading.set(true)
            }
            .catch {
                // 捕获上游出现的异常
                mLoading.set(false)
            }
            .onCompletion {
                // 请求完成
                mLoading.set(false)
            }
            .collectLatest { result ->
                result.doFailure { throwable ->
//                    Log.d("Needed_pokemon", throwable.toString())
                    _failure.value = throwable?.message ?: "failure"
                }
                result.doSuccess { value ->
                    _pokemon.postValue(value)
//                    Log.d("Needed_pokemon", value.toString())
                    emit(value)
                }
            }
    }

//    /**
//     * 方法三
//     */
//    suspend fun fetchPokemonInfo3(name: String) =
//        pokemonRepository.fetchPokemonInfo(name)
//            .onStart {
//                // 在调用 flow 请求数据之前，做一些准备工作，例如显示正在加载数据的按钮
//                mLoading.set(true)
//            }
//            .catch {
//                // 捕获上游出现的异常
//                mLoading.set(false)
//            }
//            .onCompletion {
//                // 请求完成
//                mLoading.set(false)
//            }.asLiveData()

//    /**
//     * 方法一
//     */
//    fun fetchPokemonInfo1(name: String) = viewModelScope.launch {
//        pokemonRepository.fetchPokemonInfo(name)
//            .onStart {
//                // 在调用 flow 请求数据之前，做一些准备工作，例如显示正在加载数据的按钮
//                mLoading.set(true)
//            }
//            .catch {
//                // 捕获上游出现的异常
//                mLoading.set(false)
//            }
//            .onCompletion {
//                // 请求完成
//                mLoading.set(false)
//            }
//            .collectLatest { result ->
//                result.doFailure { throwable ->
//                    _failure.value = throwable?.message ?: "failure"
//                }
//
//                result.doSuccess { value ->
//                    _pokemon.postValue(value)
//                }
//            }
//    }


    companion object {
        private val TAG = "DetailViewModel"
    }

    fun addToFavorite(movie: PokemonItemModel){
        CoroutineScope(Dispatchers.IO).launch {
            pokemonRepository.addToFavorite(
                FavoriteImageEntity(
                    accountId = movie.accountId,
                    createdAt = movie.createdAt,
                    description = movie.description,
                    lrThumbnailUrl = movie.lrThumbnailUrl,
                    name = movie.name,
                    thumbnailUrl = movie.thumbnailUrl,
                    updatedAt = movie.updatedAt
                )
            )
        }
    }

//    suspend fun checkMovie(url: String) : Boolean{
//        return pokemonRepository.checkMovie(url)
//    }

}