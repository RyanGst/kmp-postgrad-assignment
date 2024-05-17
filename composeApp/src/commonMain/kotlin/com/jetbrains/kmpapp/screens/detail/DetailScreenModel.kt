package com.jetbrains.kmpapp.screens.detail

import cafe.adriel.voyager.core.model.ScreenModel
import com.jetbrains.kmpapp.data.BasePokemonObject
import com.jetbrains.kmpapp.data.PokemonObject
import com.jetbrains.kmpapp.data.PokemonRepository
import kotlinx.coroutines.flow.Flow

class DetailScreenModel(private val museumRepository: PokemonRepository) : ScreenModel {
    fun getObject(objectId: Int): Flow<PokemonObject?> = museumRepository.getObjectById(objectId)
}
