package com.alwan.suitmediascreening.util

import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

fun CircleImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}

fun String.isPalindrome(): Boolean {
    val sb = StringBuilder(this)
    val reverseStr = sb.reverse().toString()

    return this.equals(reverseStr, ignoreCase = true)
}