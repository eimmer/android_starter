package com.bracket.datasharemain.network

import com.bracket.datasharemain.data.model.RandomRecipeResult
import com.bracket.datasharemain.data.model.RecipeInformation
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class CookingService(private val apiInterceptor: ServiceApiInterceptor) {
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

    suspend fun getRandomRecipes() : List<RecipeInformation> {
        return service.getRandomRecipes().recipes
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

