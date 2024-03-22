package regolith.data.settings.structure

import regolith.data.settings.Described
import regolith.data.settings.Keyed
import regolith.data.settings.UserInput

/**
 * Metadata used in all settings types.
 *
 * @param INPUT The primitive input type for user input of this setting.
 * @param STORED The primitive type used when writing this data to storage.
 */
sealed interface Setting<INPUT, STORED>: Keyed, Described, UserInput<INPUT, STORED>
