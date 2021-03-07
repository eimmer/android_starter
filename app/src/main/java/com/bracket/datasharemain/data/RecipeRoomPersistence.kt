package com.bracket.datasharemain.data

import com.bracket.datasharemain.data.daos.RecipeDao
import com.bracket.datasharemain.data.entities.Recipe

class RecipeRoomPersistence(private val recipeDao: RecipeDao) : RecipePersistence {

    override suspend fun save(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    override suspend fun delete(recipe: Recipe) {
        recipeDao.delete(recipe)
    }

}