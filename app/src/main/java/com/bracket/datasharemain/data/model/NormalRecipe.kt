package com.bracket.datasharemain.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NormalRecipe(
    val id: Int,
    val title: String,
    val summary: String,
    val imageUrl: String? = null) : Parcelable

