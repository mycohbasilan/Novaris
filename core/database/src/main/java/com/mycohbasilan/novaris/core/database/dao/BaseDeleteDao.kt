package com.mycohbasilan.novaris.core.database.dao

import androidx.room.Delete

/**
 * Base DAO that provides reusable **delete** operations.
 *
 * Feature modules create their own DAOs by extending this interface:
 * ```
 * @Dao
 * interface UserDao : BaseDeleteDao<UserEntity>
 * ```
 */
interface BaseDeleteDao<T> {

    /** Deletes a single [entity] matched by primary key. */
    @Delete
    suspend fun delete(entity: T)

    /** Deletes multiple [entities] matched by primary key. */
    @Delete
    suspend fun deleteAll(entities: List<T>)
}
