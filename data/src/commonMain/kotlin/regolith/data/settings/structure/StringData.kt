package regolith.data.settings.structure

import regolith.data.settings.SettingLevel
import regolith.data.settings.transformers.Transformer
import regolith.data.settings.transformers.reverse
import regolith.data.settings.transformers.then
import regolith.data.settings.validators.InputValidator
import regolith.data.settings.validators.PassingValidator

/**
 * A setting that is stored as a string.
 */
data class StringData<DATA>(
    override val key: String,
    override val name: String,
    override val dataTransformer: Transformer<String?, DATA>,
    override val defaultValue: DATA,
    override val inputTransformer: Transformer<String, DATA>,
    val dataValidator: InputValidator<DATA> = PassingValidator,
    override val inputValidator: InputValidator<String> = PassingValidator,
    override val category: String? = null,
    override val description: String? = null,
    override val level: SettingLevel = SettingLevel.DEFAULT,
): DataSetting<String, String?, DATA> {
    override fun toPrimitive(): PrimitiveSetting<String, String?> {
        return StringSetting(
            key = key,
            name = name,
            defaultValue = dataTransformer.reverseTransform(defaultValue),
            inputTransformer = inputTransformer.then(dataTransformer.reverse()),
            inputValidator = inputValidator,
            category = category,
            description = description,
            level = level,
        )
    }

    override fun toString(): String = "Setting($key)"
}
