package com.bracket.datasharemain.injection

import androidx.room.Room
import com.bracket.datasharemain.MainApplication
import com.bracket.datasharemain.data.AppDatabase
import com.bracket.datasharemain.data.RecipePersistence
import com.bracket.datasharemain.data.RecipeRoomPersistence
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class DatabaseComponentModule {

    @Provides
    @Singleton
    fun provideDatabase(context: MainApplication): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "recipe_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecipePersistence(database: AppDatabase): RecipePersistence {
        return RecipeRoomPersistence(database.recipeDao())
    }

}