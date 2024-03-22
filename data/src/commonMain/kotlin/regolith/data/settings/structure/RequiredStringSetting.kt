package regolith.data.settings.structure

import regolith.data.settings.SettingLevel
import regolith.data.settings.transformers.*
import regolith.data.settings.validators.InputValidator
import regolith.data.settings.validators.PassingValidator

data class RequiredStringSetting(
    override val key: String,
    override val name: String,
    override val defaultValue: String,
    override val level: SettingLevel = SettingLevel.DEFAULT,
    override val category: String? = null,
    override val description: String? = null,
    override val inputValidator: InputValidator<String> = PassingValidator,
    override val inputTransformer: Transformer<String, String> = NoTransformation(),
): DataSetting<String, String?, String> {
    override val dataTransformer: Transformer<String?, String> = DefaultingTransformer(defaultValue)
    override fun toPrimitive(): PrimitiveSetting<String, String?> {
        return StringSetting(
            key = key,
            name = name,
            defaultValue = defaultValue,
            inputTransformer = inputTransformer.then(NullCoalesceStringTransformer),
            inputValidator = inputValidator,
            category = category,
            description = description,
            level = level,
        )
    }
    override fun toString(): String = "Setting($key)"
}
