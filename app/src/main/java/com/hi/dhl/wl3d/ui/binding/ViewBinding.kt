package com.hi.dhl.wl3d.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hi.dhl.wl3d.R

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@BindingAdapter("bindingAvator")
fun bindingAvator(imageView: ImageView, url: String) {

    Glide.with(imageView.context)
        .load(url)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_error)
        .placeholder(R.drawable.vr)
        .into(imageView)
}

@BindingAdapter("bindingInfo")
fun bindingInfo(imageView: ImageView, url: String?) {

    Glide.with(imageView.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_error)
        .into(imageView)
}

