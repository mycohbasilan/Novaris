# :core:database

Provides the shared local persistence layer using Room. Any module that needs local storage depends on this module.

## Dependencies

| Library | Scope | Purpose |
|---|---|---|
| Room Runtime `2.7.1` | `api` | Room database engine |
| Room KTX `2.7.1` | `api` | Kotlin coroutines + Flow support for Room |
| Room Compiler `2.7.1` | `ksp` | Annotation processor for `@Dao`, `@Entity`, `@Database` |
| Room Testing `2.7.1` | `testImplementation` | In-memory database for unit tests |
| Dagger Hilt `2.59.2` | `implementation` + KSP | Dependency injection |

> **`api` scope:** Room Runtime and KTX are exposed as `api` so consuming modules can define their own `@Entity` and `@Dao` classes without re-declaring Room dependencies.

### Room Gradle Plugin

The `androidx.room` plugin is applied to configure automatic schema export:

```kotlin
room {
    schemaDirectory("$projectDir/schemas")
}
```

Schema JSON files are exported to `core/database/schemas/` on each build — commit these to track database migrations over time.

## Package Structure

```
com.mycohbasilan.novaris.core.database
├── converter/
│   └── InstantConverter.kt       # TypeConverter: Instant ↔ Long (epoch millis)
└── dao/
    ├── BaseDao.kt                # Combines insert + update + delete (full CRUD writes)
    ├── BaseInsertDao.kt          # insert(), insertAll()
    ├── BaseUpdateDao.kt          # update(), updateAll()
    └── BaseDeleteDao.kt          # delete(), deleteAll()
```

## Key Components

### Base DAOs

Composable interfaces that eliminate boilerplate in feature DAOs. Pick only what you need:

| Interface | Operations |
|---|---|
| `BaseInsertDao<T>` | `insert(entity)`, `insertAll(entities)` — both use `OnConflictStrategy.REPLACE` |
| `BaseUpdateDao<T>` | `update(entity)`, `updateAll(entities)` |
| `BaseDeleteDao<T>` | `delete(entity)`, `deleteAll(entities)` |
| `BaseDao<T>` | Combines all three — full CRUD write access |

#### Full CRUD example

```kotlin
@Dao
interface UserDao : BaseDao<UserEntity> {
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getById(id: Long): UserEntity?

    @Query("SELECT * FROM users")
    fun observeAll(): Flow<List<UserEntity>>
}
```

#### Read-only example

```kotlin
@Dao
interface ReadOnlyUserDao {
    @Query("SELECT * FROM users")
    fun observeAll(): Flow<List<UserEntity>>
}
```

#### Insert-only example

```kotlin
@Dao
interface SyncDao : BaseInsertDao<CachedItemEntity>
```

### `InstantConverter`

A Room `TypeConverter` that maps `java.time.Instant` to `Long` (epoch milliseconds) and back. Register it on your `@Database` class:

```kotlin
@TypeConverters(InstantConverter::class)
@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
```

## Usage

```kotlin
// feature/build.gradle.kts
dependencies {
    implementation(project(":core:database"))
}
```

### Defining an Entity (in a feature module)

```kotlin
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val createdAt: Instant,  // handled by InstantConverter
)
```

### Providing the Database via Hilt (in the app module)

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "novaris.db")
            .build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
}
```

