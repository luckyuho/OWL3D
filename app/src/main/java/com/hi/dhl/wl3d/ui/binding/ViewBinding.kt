package com.hi.dhl.wl3d.ui.binding

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hi.dhl.jprogressview.JProgressView
import com.hi.dhl.wl3d.R
import com.hi.dhl.wl3d.model.PokemonInfoModel
import com.hi.dhl.wl3d.model.PokemonItemModel
import com.hi.dhl.wl3d.ui.Browser.BrowserFragmentDirections
//import com.hi.dhl.wl3d.ui.detail.AlbumAdapter
import com.hi.dhl.wl3d.ui.detail.DetailActivity
import com.hi.dhl.wl3d.ui.detail.DetailsFragment
import dagger.hilt.android.internal.managers.ViewComponentManager
import timber.log.Timber

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@BindingAdapter("bindingAvator")
fun bindingAvator(imageView: ImageView, url: String) {
//    Log.d("bindingAvator: ", url)
//    imageView.load(url) {
//        crossfade(true)
//        placeholder(R.drawable.vr)
//    }
    Glide.with(imageView.context)
        .load(url)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_error)
        .placeholder(R.drawable.vr)
        .into(imageView)
}

@BindingAdapter("bindSmallImage")
fun bindingSmallImage(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.drawable.wl3d)
        size(280, 280)
    }
}

@BindingAdapter("bindLoading")
fun bindingLoading(swipe: SwipeRefreshLayout, isLoading: Boolean) {
    Timber.tag("bindingLoading").e(" isLoading = ${isLoading}")
    swipe.isRefreshing = isLoading
    if (!isLoading) swipe.isEnabled = false
}

@BindingAdapter("bindingToggle")
fun bindingToggle(toggleButton: ToggleButton, isCheck: Boolean) {
//    Timber.tag("bindingLoading").e(" isLoading = ${isLoading}")
//    swipe.isRefreshing = isLoading
//    if (!isLoading) swipe.isEnabled = false
//    toggleButton.setOnClickListener {
        toggleButton.isChecked = isCheck
//        Toast.makeText(toggleButton.context, "${toggleButton.isChecked}", Toast.LENGTH_SHORT).show()
//    }
}

//@BindingAdapter("bindProgressValue", "bindProgressMaxValue")
//fun bindingProgressView(progress: JProgressView, progressValue: Int, maxProgressValue: Int) {
//    progress
//        .setProgress(progressValue.toFloat())
//        .setMaxProgress(maxProgressValue)
//        .startAnimal()
//}

@BindingAdapter("bindFinish")
fun bindingFinish(view: View, finish: Boolean) {
    val ctx = view.context
    if (ctx is Activity && finish) {
        view.setOnClickListener { ctx.finish() }
    }
}

//@BindingAdapter("bindClick")
//fun bindingClick(view: View, model: PokemonItemModel) {
//    view.setOnClickListener {
////        DetailActivity.jumpAcrtivity(
////            view.context,
////            model
////        )
//        Log.d("showargsClick", view.context.packageName)
//        Log.d("showargsClick", view.context.packageResourcePath)
//        Log.d("showargsClick", view.context.packageCodePath)
//
//
////        val action = BrowserFragmentDirections.actionNavMovieToNavDetails(model)
////        findNavController(view).navigate(action)
//    }
//}

@BindingAdapter("bindingInfo")
fun bindingInfo(imageView: ImageView, url: String?) {
    if (url!=null) {
//        imageView.load(url) {
////            crossfade(true)
////            placeholder(R.mipmap.wl3d)
//        }
        Glide.with(imageView.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_error)
            .into(imageView)
    }else{
        Log.d("bindinginfodatanull", "datanull")
    }
}

//@BindingAdapter("bindAdapter", "bindData")
//fun bindingAdapter(
//    recyclerView: RecyclerView,
//    albumAdapter: AlbumAdapter,
//    data: List<PokemonInfoModel.AlbumModel>?
//) {
//    data?.let {
//        recyclerView.adapter = albumAdapter
//        albumAdapter.submitList(data)
//        albumAdapter.notifyDataSetChanged()
//    }
//
//}

//@BindingAdapter("bindAdapter", "bindData")
//fun bindingAdapter(
//    recyclerView: RecyclerView,
//    albumAdapter: AlbumAdapter,
//    data: List<PokemonInfoModel>?
//) {
//    data?.let {
//        recyclerView.adapter = albumAdapter
//        albumAdapter.submitList(data)
//        albumAdapter.notifyDataSetChanged()
//    }
//
//}