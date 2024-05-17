package com.jetbrains.kmpapp.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PokemonRepository(
    private val pokemonApi: PokemonApi,
    private val pokemonStorage:  PokemonStorage,
) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }
    suspend fun refresh() {
        pokemonStorage.saveObjects(pokemonApi.getData())
    }

    fun getObjects() = pokemonStorage.getObjects()
    fun getObjectById(objectId: Int) = pokemonStorage.getObjectById(objectId)
}
