package com.bracket.datasharemain.data

import com.bracket.datasharemain.data.model.NormalRecipe

interface RecipePersistence {
    suspend fun save(recipe: NormalRecipe)
    suspend fun delete(recipe: NormalRecipe)
    suspend fun getAll(): List<NormalRecipe>
    suspend fun getRecipe(id:Int): NormalRecipe?
}