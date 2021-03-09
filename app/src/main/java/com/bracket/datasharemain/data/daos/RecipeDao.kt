package com.bracket.datasharemain.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bracket.datasharemain.data.entities.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipe WHERE recipe.id == :id")
    fun get(id:Int):Recipe?

    @Insert
    fun insert(vararg recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}