package regolith.data.settings.transformers

/**
 * Transformer that does not modify input/output at all.
 */
class NoTransformation<T>: Transformer<T, T> {
    override fun transform(data: T): T = data
    override fun reverseTransform(data: T): T = data
}
