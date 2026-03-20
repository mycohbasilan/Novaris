package com.mycohbasilan.novaris.core.network.util

import kotlinx.serialization.json.Json

/**
 * Shared [Json] instance configured with sensible defaults
 * for the entire app.
 *
 * - `ignoreUnknownKeys`  — API responses may evolve; unknown fields won't crash.
 * - `isLenient`          — Tolerates slightly malformed JSON from servers.
 * - `encodeDefaults`     — Includes default-valued properties in serialized output.
 * - `explicitNulls`      — Omits `null` values when serializing, accepts missing keys when
 *                          deserializing.
 */
object JsonProvider {
    val json: Json =
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
            explicitNulls = false
        }
}
