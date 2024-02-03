package com.example.pokedex.repository

import com.example.lesn1.repository.PokService
import com.example.lesn1.repository.PokemonResponse
import com.example.lesn1.repository.Pokemons
import retrofit2.Response

class PokRepository {
    suspend fun getPokemonResponse(): Response<PokemonResponse> =
        PokService.pokService.getPokemonResponse()

    suspend fun getPokemons(name:String): Response<Pokemons> =
        PokService.pokService.getPokemons(name)
}