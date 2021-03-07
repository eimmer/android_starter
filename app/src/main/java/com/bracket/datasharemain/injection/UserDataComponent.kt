package com.bracket.datasharemain.injection

import com.bracket.datasharemain.ui.main.MainFragment
import com.bracket.datasharemain.ui.recipe_details.RecipeDetailFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, DatabaseComponentModule::class, ApplicationModule::class])
@Singleton
interface UserDataComponent {
    fun inject(fragment: MainFragment)

    fun inject(fragment: RecipeDetailFragment)
}