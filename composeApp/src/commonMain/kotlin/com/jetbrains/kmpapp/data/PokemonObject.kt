package com.jetbrains.kmpapp.data

import kotlinx.serialization.Serializable
@Serializable
data class Sprites(
    val front_default: String
)
@Serializable
data class PokemonObject(
    val id: Int,
    val name: String,
    val imageUrl: String
)
