package com.bracket.datasharemain.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bracket.datasharemain.data.daos.FavoriteDao
import com.bracket.datasharemain.data.daos.RecipeDao
import com.bracket.datasharemain.data.entities.Favorite
import com.bracket.datasharemain.data.entities.Recipe

@Database(entities = [Recipe::class, Favorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    abstract fun favoriteDao(): FavoriteDao
}