package com.bracket.datasharemain.ui.recipe_details

import androidx.lifecycle.*
import com.bracket.datasharemain.data.RecipePersistence
import com.bracket.datasharemain.data.entities.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val recipe: Recipe,
    private val recipePersistence: RecipePersistence
) : ViewModel() {

    private var isFavorite = false

    private val announceFavorite = MutableLiveData(false)
    val isRecipeFavorite: LiveData<Boolean> = announceFavorite

    private val announceRecipe = MutableLiveData(recipe)
    val liveRecipe: LiveData<Recipe> = announceRecipe

    fun markAsFavorite() {
        isFavorite = !isFavorite
        announceFavorite.value = isFavorite

        if (isFavorite) saveRecipeFavorite()
        else deleteRecipeFavorite()
    }

    private fun deleteRecipeFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            recipePersistence.delete(recipe)
        }
    }

    private fun saveRecipeFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            recipePersistence.save(recipe)
        }
    }

    class Factory(private val recipe: Recipe, private val recipePersistence: RecipePersistence) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return RecipeDetailViewModel(recipe, recipePersistence) as T
        }
    }
}