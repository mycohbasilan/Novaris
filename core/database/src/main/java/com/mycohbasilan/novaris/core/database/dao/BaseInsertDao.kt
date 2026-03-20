package com.mycohbasilan.novaris.core.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Base DAO that provides reusable **insert** operations.
 *
 * Feature modules create their own DAOs by extending this interface:
 * ```
 * @Dao
 * interface UserDao : BaseInsertDao<UserEntity>
 * ```
 */
interface BaseInsertDao<T> {

    /** Inserts a single [entity], replacing on conflict. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T)

    /** Inserts multiple [entities], replacing on conflict. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<T>)
}
