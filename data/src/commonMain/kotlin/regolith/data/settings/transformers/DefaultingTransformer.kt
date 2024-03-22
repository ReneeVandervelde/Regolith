package regolith.data.settings.transformers

/**
 * A transformer that removes nulls by defaulting to a preset value
 */
class DefaultingTransformer<T: Any>(
    private val default: T,
): Transformer<T?, T> {
    override fun transform(data: T?): T {
        return data ?: default
    }

    override fun reverseTransform(data: T): T {
        return data
    }
}
