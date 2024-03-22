package regolith.data.settings

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import regolith.data.Settings
import regolith.data.settings.structure.PrimitiveSetting
import regolith.data.settings.structure.StringSetting

internal class SettingsDatabaseAccess(
    private val database: Deferred<Settings>,
): SettingsAccess {
    override fun <STORED> observeSetting(setting: PrimitiveSetting<*, STORED>): Flow<STORED> {
        val queries = flow { emit(database.await().settingsQueries) }
        return when (setting) {
            is StringSetting -> queries.flatMapLatest { it.getStringValue(setting.key).asFlow() }
                .map {  it.executeAsOneOrNull() }
                .map { it?.stringValue }
        }.map { it as STORED }
    }

    override suspend fun <STORED> getSetting(setting: PrimitiveSetting<*, STORED>): STORED {
        return when (setting) {
            is StringSetting -> database.await().settingsQueries.getStringValue(setting.key)
                .executeAsOneOrNull()
                ?.stringValue
        } as STORED
    }

    override suspend fun <STORED> writeSetting(setting: PrimitiveSetting<*, STORED>, value: STORED) {
        when (setting) {
            is StringSetting -> database.await().settingsQueries.setStringValue(
                key = setting.key,
                stringValue = value as String,
            )
        }
    }
}

internal fun createDatabaseAccess(scope: CoroutineScope, driver: SqlDriver): SettingsAccess {
    val database = scope.async {
        Settings.Schema.create(driver).await()
        Settings(driver)
    }

    return SettingsDatabaseAccess(database)
}
