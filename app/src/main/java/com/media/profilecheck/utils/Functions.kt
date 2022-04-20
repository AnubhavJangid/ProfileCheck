package com.media.profilecheck.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.media.profilecheck.R

private fun Context.getGlideRequestOptions(): RequestOptions {
    val requestOptions = RequestOptions()
    requestOptions.error(R.drawable.image_placeholder)
    requestOptions.placeholder(R.drawable.image_placeholder)
    return requestOptions
}

fun ImageView.loadImage(
    url: String? = "",
    requestOptions: RequestOptions = context.getGlideRequestOptions()
) {
    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .into(this);
}