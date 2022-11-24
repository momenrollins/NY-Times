package com.momen.nytimes.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
) : Parcelable