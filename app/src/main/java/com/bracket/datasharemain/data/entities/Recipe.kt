package com.bracket.datasharemain.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(@PrimaryKey val id: Int, val title: String, val summary: String, val imageUrl:String? = null)