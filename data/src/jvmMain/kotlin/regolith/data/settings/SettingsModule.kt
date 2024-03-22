package regolith.data.settings

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.File

class SettingsModule(
    databaseFile: File,
    migrationScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) {
    private val driver = JdbcSqliteDriver("jdbc:sqlite:${databaseFile.absolutePath}")
    val settingsAccess = createDatabaseAccess(migrationScope, driver)
}
