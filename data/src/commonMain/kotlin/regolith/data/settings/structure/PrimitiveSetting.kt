package regolith.data.settings.structure

import regolith.data.settings.transformers.Transformer

/**
 * Key/value data for an application configuration.
 */
sealed interface PrimitiveSetting<INPUT, STORED>: Setting<INPUT, STORED> {
    /**
     * The initial value when the application is first run.
     */
    val defaultValue: STORED

    /**
     * Transformer for changing user-supplied input into the setting data type.
     */
    val inputTransformer: Transformer<INPUT, STORED>
}
