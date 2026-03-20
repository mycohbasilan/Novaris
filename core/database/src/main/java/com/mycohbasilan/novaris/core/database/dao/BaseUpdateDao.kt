package com.mycohbasilan.novaris.core.database.dao

import androidx.room.Update

/**
 * Base DAO that provides reusable **update** operations.
 *
 * Feature modules create their own DAOs by extending this interface:
 * ```
 * @Dao
 * interface UserDao : BaseUpdateDao<UserEntity>
 * ```
 */
interface BaseUpdateDao<T> {

    /** Updates a single [entity] matched by primary key. */
    @Update
    suspend fun update(entity: T)

    /** Updates multiple [entities] matched by primary key. */
    @Update
    suspend fun updateAll(entities: List<T>)
}
