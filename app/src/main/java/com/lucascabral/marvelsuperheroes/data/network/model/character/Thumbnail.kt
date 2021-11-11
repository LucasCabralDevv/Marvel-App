package com.lucascabral.marvelsuperheroes.data.network.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    val path: String,
    val extension: String
) : Parcelable
