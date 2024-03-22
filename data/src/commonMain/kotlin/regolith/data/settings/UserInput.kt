package regolith.data.settings

import regolith.data.settings.validators.InputValidator

/**
 * Fields that allow a setting to be transformed to/from a user form field.
 *
 * @param INPUT The primitive input type for user input of this setting.
 * @param STORED The primitive type used when writing this data to storage.
 */
interface UserInput<INPUT, STORED> {
    /**
     * Validator to be used on user-supplied input fields.
     */
    val inputValidator: InputValidator<INPUT>
}
