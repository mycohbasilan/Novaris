package com.mycohbasilan.novaris.core.database.converter

import androidx.room.TypeConverter
import java.time.Instant

/**
 * Room [TypeConverter] for [Instant] ↔ [Long] (epoch milliseconds).
 *
 * Register this converter on your `@Database` class:
 * ```
 * @TypeConverters(InstantConverter::class)
 * @Database(entities = [...], version = 1)
 * abstract class AppDatabase : RoomDatabase()
 * ```
 */
class InstantConverter {

    @TypeConverter
    fun fromInstant(value: Instant?): Long? = value?.toEpochMilli()

    @TypeConverter
    fun toInstant(value: Long?): Instant? = value?.let { Instant.ofEpochMilli(it) }
}
