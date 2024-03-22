package regolith.data.settings.transformers

/**
 * Transformer for string data that trims whitespace from the input.
 *
 * Note that this transformer only trims in the primary direction.
 */
object TrimmingTransformer: Transformer<String, String> {
    override fun transform(data: String): String = data.trim()
    override fun reverseTransform(data: String): String = data
}
