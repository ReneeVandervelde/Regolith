package regolith.data.settings.validators

/**
 * Verifies that user-supplied inputs are valid for a setting.
 */
interface InputValidator<in INPUT> {
    /**
     * Check [input] for validity or return an error to correct the problem.
     */
    fun validate(input: INPUT): ValidationResult
}

/**
 * Combines multiple validators into a single validator.
 *
 * This preserves all validation results and combines any errors into
 * a single failure result. Errors are in order and not de-duplicated
 * from each validator applied.
 */
private class CompositeInputValidator<INPUT>(
    val validators: List<InputValidator<INPUT>>,
): InputValidator<INPUT> {
    override fun validate(input: INPUT): ValidationResult {
        val results = validators.map { it.validate(input) }

        if (results.none { it !is ValidationResult.Success }) {
            return ValidationResult.Success
        }

        return ValidationResult.Failed(
            results.filterIsInstance<ValidationResult.Failed>().flatMap { it.reasons }
        )
    }
}

/**
 * Combine two input validators.
 */
operator fun <INPUT> InputValidator<INPUT>.plus(other: InputValidator<INPUT>): InputValidator<INPUT> {
    return when {
        this is CompositeInputValidator && other is CompositeInputValidator -> {
            CompositeInputValidator(
                validators = this.validators + other.validators
            )
        }
        this is CompositeInputValidator -> {
            CompositeInputValidator(
                validators = this.validators + other
            )
        }
        other is CompositeInputValidator -> {
            CompositeInputValidator(
                validators = listOf(this) + other.validators
            )
        }
        else -> {
            CompositeInputValidator(
                validators = listOf(this, other)
            )
        }
    }
}
