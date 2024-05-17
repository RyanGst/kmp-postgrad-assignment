package com.jetbrains.kmpapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.CancellationException
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json



@Serializable
data class PokemonResponse(
    val results: List<PokemonResult>
)

@Serializable
data class PokemonResult(
    val name: String,
    val url: String
)

@Serializable
data class PokemonDetail(
    val id: Int,
    val name: String,
    val sprites: Sprites
)

interface PokemonApiI {
    suspend fun getData(): List<PokemonObject>
}

class PokemonApi(private val client: HttpClient) : PokemonApiI {
    companion object {
        private const val API_URL = "https://pokeapi.co/api/v2/pokemon"
    }

    override suspend fun getData(): List<PokemonObject> {
        return try {
            val json = Json { ignoreUnknownKeys = true }
            val response: HttpResponse = client.get("$API_URL?limit=20")
            val responseBody: String = response.body()
            val pokemonResponse: PokemonResponse = json.decodeFromString(responseBody)
            val pokemonList = mutableListOf<PokemonObject>()

            for (result in pokemonResponse.results) {
                val detailResponse: HttpResponse = client.get(result.url)
                val detailBody: String = detailResponse.body()
                val pokemonDetail: PokemonDetail = json.decodeFromString(detailBody)

                pokemonList.add(
                    PokemonObject(
                        id = pokemonDetail.id,
                        name = result.name,
                        imageUrl = pokemonDetail.sprites.front_default
                    )
                )
            }

            pokemonList
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }
}