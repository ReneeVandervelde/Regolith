package regolith.data.settings.validators

/**
 * Validator that always passes.
 */
object PassingValidator: InputValidator<Any?> {
    override fun validate(input: Any?): ValidationResult = ValidationResult.Success
}
