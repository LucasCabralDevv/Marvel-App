package com.lucascabral.marvelsuperheroes.data.network.model.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
) : Parcelable
