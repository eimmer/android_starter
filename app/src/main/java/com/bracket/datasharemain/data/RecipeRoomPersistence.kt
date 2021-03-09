package com.bracket.datasharemain.data

import com.bracket.datasharemain.data.daos.RecipeDao
import com.bracket.datasharemain.data.model.NormalRecipe
import com.bracket.datasharemain.data.model.toDataEntityRecipe
import com.bracket.datasharemain.data.model.toNormalRecipe

class RecipeRoomPersistence(private val recipeDao: RecipeDao) : RecipePersistence {

    override suspend fun save(recipe: NormalRecipe) {
        recipeDao.insert(recipe.toDataEntityRecipe())
    }

    override suspend fun delete(recipe: NormalRecipe) {
        recipeDao.delete(recipe.toDataEntityRecipe())
    }

    override suspend fun getAll(): List<NormalRecipe> {
        return recipeDao.getAll().map { it.toNormalRecipe() }
    }

}