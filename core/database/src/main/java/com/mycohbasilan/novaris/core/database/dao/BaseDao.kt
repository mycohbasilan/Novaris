package com.mycohbasilan.novaris.core.database.dao

/**
 * Convenience base DAO that combines **insert**, **update**, and **delete**
 * operations into a single interface.
 *
 * Use this when a feature DAO needs full CRUD write access:
 * ```
 * @Dao
 * interface UserDao : BaseDao<UserEntity> {
 *     @Query("SELECT * FROM users WHERE id = :id")
 *     suspend fun getById(id: Long): UserEntity?
 * }
 * ```
 *
 * If a DAO only needs a subset of operations, extend the individual
 * interfaces ([BaseInsertDao], [BaseUpdateDao], [BaseDeleteDao]) instead.
 */
interface BaseDao<T> :
    BaseInsertDao<T>,
    BaseUpdateDao<T>,
    BaseDeleteDao<T>
