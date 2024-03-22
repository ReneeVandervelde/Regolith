package regolith.data.settings.transformers

/**
 * Transformer that interprets empty input as null.
 */
object NullCoalesceStringTransformer: Transformer<String, String?> {
    override fun transform(data: String): String? = data.takeIf { it.isNotBlank() }
    override fun reverseTransform(data: String?): String = data.orEmpty()
}
