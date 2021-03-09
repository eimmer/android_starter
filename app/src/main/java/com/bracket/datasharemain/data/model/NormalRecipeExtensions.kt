package com.bracket.datasharemain.data.model

import com.bracket.datasharemain.data.entities.Recipe

fun NormalRecipe.toDataEntityRecipe():Recipe{
    return Recipe(id, title, summary, imageUrl)
}

fun Recipe.toNormalRecipe(): NormalRecipe {
    return NormalRecipe(id, title, summary, imageUrl)
}

fun List<Recipe>.toNormalRecipe(): List<NormalRecipe> {
    return map { NormalRecipe(it.id, it.title, it.summary, it.imageUrl) }
}

fun RecipeInformation.toNormalRecipe(): NormalRecipe {
    return NormalRecipe(this.id, this.title, this.summary, this.image)
}

fun List<RecipeInformation>.toNormalRecipeList(): List<NormalRecipe> {
    return map { it.toNormalRecipe() }
}