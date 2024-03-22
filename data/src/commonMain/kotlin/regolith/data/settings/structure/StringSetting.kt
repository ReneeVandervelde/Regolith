package regolith.data.settings.structure

import regolith.data.settings.SettingLevel
import regolith.data.settings.transformers.NullCoalesceStringTransformer
import regolith.data.settings.transformers.Transformer
import regolith.data.settings.validators.InputValidator
import regolith.data.settings.validators.PassingValidator

/**
 * A Primitive String Setting.
 */
data class StringSetting(
    override val key: String,
    override val name: String,
    override val defaultValue: String? = null,
    override val inputTransformer: Transformer<String, String?> = NullCoalesceStringTransformer,
    override val inputValidator: InputValidator<String> = PassingValidator,
    override val category: String? = null,
    override val description: String? = null,
    override val level: SettingLevel = SettingLevel.DEFAULT,
): PrimitiveSetting<String, String?> {
    override fun toString(): String = "Setting($key)"
}

