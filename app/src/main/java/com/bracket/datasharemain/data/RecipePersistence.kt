package com.bracket.datasharemain.data

import com.bracket.datasharemain.data.entities.Recipe

interface RecipePersistence {
    suspend fun save(recipe: Recipe)
    suspend fun delete(recipe: Recipe)
}