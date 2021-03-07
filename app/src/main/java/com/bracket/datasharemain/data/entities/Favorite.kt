package com.bracket.datasharemain.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(@PrimaryKey val recipe_id: Int)