package com.lucascabral.marvelsuperheroes.data.network.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
) : Parcelable
