package com.bracket.datasharemain.ui.recipe_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeDetailViewModel : ViewModel() {

    private var isFavorite = false

    private val announceFavorite = MutableLiveData(false)
    val isRecipeFavorite:LiveData<Boolean> = announceFavorite

    fun markAsFavorite(){
        isFavorite = !isFavorite
        announceFavorite.value = isFavorite
    }
}