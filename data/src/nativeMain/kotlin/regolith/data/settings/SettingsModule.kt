package regolith.data.settings

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import regolith.data.Settings

class SettingsModule(
    databaseName: String = "settings.db",
    migrationScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) {
    private val driver = NativeSqliteDriver(Settings.Schema.synchronous(), databaseName)
    val settingsAccess = createDatabaseAccess(migrationScope, driver)
}
