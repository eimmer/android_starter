package com.bracket.datasharemain.ui.favorites

import androidx.lifecycle.*
import com.bracket.datasharemain.data.RecipePersistence
import com.bracket.datasharemain.data.model.NormalRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteRecipesViewModel(private val persistence: RecipePersistence) : ViewModel() {

    private val announceRecipes = MutableLiveData<List<NormalRecipe>>()
    val favoriteRecipes: LiveData<List<NormalRecipe>> = announceRecipes

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val list = persistence.getAll()
            announceRecipes.postValue(list)
        }
    }

    class Factory(private val persistence: RecipePersistence) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return FavoriteRecipesViewModel(persistence) as T
        }
    }
}