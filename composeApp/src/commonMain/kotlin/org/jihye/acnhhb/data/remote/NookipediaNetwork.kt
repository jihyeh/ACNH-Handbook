package org.jihye.acnhhb.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json
import org.jihye.acnhhb.BuildConfig

class NookipediaNetwork {
    val httpClient = createHttpClient()

    private fun createHttpClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                val json = Json {
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                }
                register(ContentType.Application.Json, KotlinxSerializationConverter(json))
                register(ContentType.Text.Plain, KotlinxSerializationConverter(json))
            }
            install(Logging) { level = LogLevel.ALL }
            install(HttpTimeout) {
                connectTimeoutMillis = TIMEOUT_MILLIS
                requestTimeoutMillis = TIMEOUT_MILLIS
                socketTimeoutMillis = TIMEOUT_MILLIS
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_HOST
                }
                header("X-API-KEY", BuildConfig.API_KEY)
                header("Accept-Version", API_VERSION)
            }
        }
    }

    suspend inline fun <reified T : Any> get(
        path: String,
        parameters: Map<String, String?> = emptyMap(),
    ): T {
        return httpClient
            .get(path) {
                parameters.forEach { (key, value) ->
                    if (value != null) parameter(key, value)
                }
            }
            .body()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 6_000L
        private const val API_VERSION = "1.3.0"
        const val BASE_HOST = "api.nookipedia.com"
    }
}
