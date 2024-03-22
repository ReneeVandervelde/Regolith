package regolith.data.settings.structure

import regolith.data.settings.transformers.Transformer

/**
 * A setting that can be represented by a structured data type.
 *
 * @param DATA The structured data type for the setting.
 */
interface DataSetting<INPUT, STORED, DATA>: Setting<INPUT, STORED> {
    /**
     * The initial value when the application is first run.
     */
    val defaultValue: DATA

    /**
     * Transformer to convert data to/from its primitive type.
     */
    val dataTransformer: Transformer<STORED, DATA>

    /**
     * Transformer for changing user-supplied input into the setting data type.
     */
    val inputTransformer: Transformer<INPUT, DATA>

    /**
     * Get the primitive setting definition used for the stored data.
     */
    fun toPrimitive(): PrimitiveSetting<INPUT, STORED>
}
