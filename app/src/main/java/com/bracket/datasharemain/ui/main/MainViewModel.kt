package com.bracket.datasharemain.ui.main

import androidx.lifecycle.*
import com.bracket.datasharemain.data.model.NormalRecipe
import com.bracket.datasharemain.network.CookingService
import com.bracket.datasharemain.data.model.RecipeInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

abstract class MainViewEvent

class RecipeSelectedEvent(val recipeInformation: NormalRecipe) : MainViewEvent()

class MainViewModel(private val cookingService: CookingService) : ViewModel() {

    private val mutableRecipeList = MutableLiveData<List<NormalRecipe>>()
    val recipes: LiveData<List<NormalRecipe>> = mutableRecipeList

    private val announceSelectedRecipe = MutableLiveData<NormalRecipe?>()
    val selectedRecipe:LiveData<NormalRecipe?> = announceSelectedRecipe

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val recipes = cookingService.getRandomRecipes()
            mutableRecipeList.postValue(recipes)
        }
    }

    fun newEvent(event: MainViewEvent) {
        if(event is RecipeSelectedEvent) {
            announceSelectedRecipe.value = event.recipeInformation
            announceSelectedRecipe.value = null
        }
        else throw IllegalStateException("Unknown event ${event.javaClass.simpleName} fired.")
    }

    class Factory(private val cookingService: CookingService) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(cookingService) as T
        }
    }
}