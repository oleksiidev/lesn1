package com.example.lesn1.repository

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokService {
    @GET("https://pokeapi.co/api/v2/pokemon/")
    suspend fun getPokemonResponse(): Response<PokemonResponse>

    companion object{
        private const val POK_URL = "https://pokeapi.co/api/v2/"

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder().baseUrl(POK_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
        val pokService: PokService by lazy {
            retrofit.create(PokService::class.java)
        }
    }
}