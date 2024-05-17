package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.InMemoryPokemonStorage
import com.jetbrains.kmpapp.data.PokemonApi
import com.jetbrains.kmpapp.data.PokemonRepository
import com.jetbrains.kmpapp.data.PokemonStorage
import com.jetbrains.kmpapp.screens.detail.DetailScreenModel
import com.jetbrains.kmpapp.screens.list.ListScreenModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = false }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }

        }
    }

    single<PokemonApi> { PokemonApi(get()) }
    single<PokemonStorage> { InMemoryPokemonStorage() }
    single {
        PokemonRepository(get(), get()).apply {
            print("PokemonRepository initialize")
            initialize()
        }
    }
}

val screenModelsModule = module {
    factoryOf(::ListScreenModel)
    factoryOf(::DetailScreenModel)
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            screenModelsModule,
        )
    }
}
