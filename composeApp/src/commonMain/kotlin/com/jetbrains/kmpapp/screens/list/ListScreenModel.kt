package com.jetbrains.kmpapp.screens.list

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.kmpapp.data.BasePokemonObject
import com.jetbrains.kmpapp.data.PokemonObject
import com.jetbrains.kmpapp.data.PokemonRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ListScreenModel(museumRepository: PokemonRepository) : ScreenModel {
    val objects: StateFlow<List<PokemonObject>> =
        museumRepository.getObjects()
            .stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
