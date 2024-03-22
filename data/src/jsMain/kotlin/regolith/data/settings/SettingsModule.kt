package regolith.data.settings

import app.cash.sqldelight.driver.worker.WebWorkerDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.w3c.dom.Worker

class SettingsModule(
    migrationScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
    private val driver = WebWorkerDriver(
        Worker(
            js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")
        )
    )
    val settingsAccess = createDatabaseAccess(migrationScope, driver)
}
