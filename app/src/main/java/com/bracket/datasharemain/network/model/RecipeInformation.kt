package com.bracket.datasharemain.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class RandomRecipeResult(val recipes: List<RecipeInformation>)

@Parcelize
data class RecipeInformation(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstructions>,
    val cheap: Boolean,
    val creditsText: String,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Double,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val ketogenic: Boolean,
    val license: String,
    val lowFodmap: Boolean,
    val occasions: List<String>,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int,
    val whole30: Boolean,
    val winePairing: WinePairing
) : Parcelable

@Parcelize
data class ExtendedIngredient(
    val aisle: String,
    val amount: Double,
    val consitency: String,
    val id: Int,
    val image: String,
    val measures: Measures,
    val meta: List<String>,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String
) : Parcelable

@Parcelize
data class WinePairing(
    val pairedWines: List<String>,
    val pairingText: String,
    val productMatches: List<ProductMatche>
) : Parcelable

@Parcelize
data class Measures(
    val metric: Metric,
    val emperical: Emperical
) : Parcelable

@Parcelize
data class Metric(
    val amount: Double,
    val unitLong: String,
    val unitShort: String
) : Parcelable

@Parcelize
data class Emperical(
    val amount: Double,
    val unitLong: String,
    val unitShort: String
) : Parcelable

@Parcelize
data class ProductMatche(
    val averageRating: Double,
    val description: String,
    val id: Int,
    val imageUrl: String,
    val link: String,
    val price: String,
    val ratingCount: Double,
    val score: Double,
    val title: String
) : Parcelable


@Parcelize
data class AnalyzedInstructions(val name: String, val steps: List<Step>) : Parcelable

@Parcelize
data class Step(
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
) : Parcelable


@Parcelize
data class Ingredient(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String
) : Parcelable