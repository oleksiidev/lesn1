package com.example.lesn1.repository

import retrofit2.Response

class PokRepository {

    suspend fun getPokResponse(): Response<PokemonResponse> = PokService.pokService.getPokemonResponse()
}