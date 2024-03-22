package regolith.data.settings.transformers

/**
 * A bijective transformation between two types.
 */
interface Transformer<A, B> {
    fun transform(data: A): B
    fun reverseTransform(data: B): A
}

/**
 * Reverse the current Transformer.
 */
fun <A, B> Transformer<A, B>.reverse(): Transformer<B, A> {
    return ReversedTransformer(this)
}

/**
 * Chain two transformers together.
 */
fun <INPUT, A, B> Transformer<INPUT, A>.then(next: Transformer<A, B>): Transformer<INPUT, B> {
    return CompositeTransformer(
        left = this,
        right = next
    )
}

/**
 * Reverses the direction of a transformer.
 */
private class ReversedTransformer<A, B>(
    private val delegate: Transformer<A, B>,
): Transformer<B, A> {
    override fun transform(data: B): A {
        return delegate.reverseTransform(data)
    }
    override fun reverseTransform(data: A): B {
        return delegate.transform(data)
    }
}

/**
 * Combines multiple transformers into a single transformer, applying in order.
 */
private class CompositeTransformer<INPUT, A, B>(
    private val left: Transformer<INPUT, A>,
    private val right: Transformer<A, B>,
): Transformer<INPUT, B> {
    override fun reverseTransform(data: B): INPUT {
        return right.reverseTransform(data).let { left.reverseTransform(it) }
    }

    override fun transform(data: INPUT): B {
        return left.transform(data).let { right.transform(it) }
    }
}
