package com.jetbrains.kmpapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface PokemonStorage {
    suspend fun saveObjects(newObjects: List<PokemonObject>)

    fun getObjectById(objectId: Int): Flow<PokemonObject?>

    fun getObjects(): Flow<List<PokemonObject>>
}

class InMemoryPokemonStorage : PokemonStorage {
    private val storedObjects = MutableStateFlow(emptyList<PokemonObject>())

    override suspend fun saveObjects(newObjects: List<PokemonObject>) {
        storedObjects.value = newObjects
    }

    override fun getObjectById(objectId: Int): Flow<PokemonObject?> {
        return storedObjects.map { objects ->
            objects.find { it.id == objectId }
        }
    }

    override fun getObjects(): Flow<List<PokemonObject>> = storedObjects
}
