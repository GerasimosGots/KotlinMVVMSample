package com.gerasimosGk.kotlinmvvmsample.presentation

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.gerasimosGk.kotlinmvvmsample.R

/**
 * Created by Gerasimos on 6/12/2021
 *
 * Extensions used of View and View components
 */
fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.color.placeholder_color)
        .into(this)
}

fun ImageView.loadImageWithBottomCorners(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .transform(CenterCrop(), GranularRoundedCorners(0f,0f, 80f, 80f))
        .placeholder(R.color.placeholder_color)
        .into(this)
}

fun View?.visible(shouldShow: Boolean = false) {
    this?.let {
        if (shouldShow)
            it.visibility = View.VISIBLE
        else
            it.visibility = View.GONE
    }
}