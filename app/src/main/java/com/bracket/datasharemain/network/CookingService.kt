package com.bracket.datasharemain.network

import com.bracket.datasharemain.data.model.NormalRecipe
import com.bracket.datasharemain.data.model.RandomRecipeResult
import com.bracket.datasharemain.data.model.RecipeInformation
import com.bracket.datasharemain.data.model.toNormalRecipeList
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class CookingService(apiInterceptor: ServiceApiInterceptor) {
    companion object {
        private const val BASE_URL = "https://api.spoonacular.com/"
    }

    private val service: CookingApi

    init {
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(apiInterceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        service = retrofit.create(CookingApi::class.java)
    }

    suspend fun getRandomRecipes(): List<NormalRecipe> {
        return service.getRandomRecipes().recipes.toNormalRecipeList()
    }

}

interface CookingApi {
    @GET("recipes/random?number=5")
    suspend fun getRandomRecipes(): RandomRecipeResult

    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(@Path("id") recipeId: Int): RecipeInformation

    @GET("food/jokes/random")
    suspend fun getRandomJoke(): String
}

