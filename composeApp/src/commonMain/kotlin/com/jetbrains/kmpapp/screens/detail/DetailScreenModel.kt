package com.jetbrains.kmpapp.screens.detail

import cafe.adriel.voyager.core.model.ScreenModel
import com.jetbrains.kmpapp.data.PokemonObject
import com.jetbrains.kmpapp.data.PokemonRepository
import kotlinx.coroutines.flow.Flow

class DetailScreenModel(private val pokemonRepository: PokemonRepository) : ScreenModel {
    fun getObject(objectId: Int): Flow<PokemonObject?> = pokemonRepository.getObjectById(objectId)
}
