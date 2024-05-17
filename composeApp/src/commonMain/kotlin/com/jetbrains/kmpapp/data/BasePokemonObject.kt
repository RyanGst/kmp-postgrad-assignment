package com.jetbrains.kmpapp.data

import kotlinx.serialization.Serializable

@Serializable
data class BasePokemonObject(
    val name: String,
    val url: String
)
