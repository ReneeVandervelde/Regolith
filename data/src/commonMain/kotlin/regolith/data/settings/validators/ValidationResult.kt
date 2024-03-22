package regolith.data.settings.validators

/**
 * Result of a validation check.
 */
sealed interface ValidationResult {
    /**
     * Unsuccessful validation, with a list of errors.
     */
    data class Failed(val reasons: List<Error>): ValidationResult

    /**
     * Successful validation.
     */
    object Success: ValidationResult
}
