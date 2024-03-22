package regolith.data.settings

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import regolith.data.Settings

class SettingsModule(
    context: Context,
    migrationScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) {
    private val driver = AndroidSqliteDriver(Settings.Schema.synchronous(), context, "settings.db")
    val settingsAccess = createDatabaseAccess(migrationScope, driver)
}
