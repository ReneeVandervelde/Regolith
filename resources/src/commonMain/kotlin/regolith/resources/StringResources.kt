package regolith.resources

import kotlin.jvm.JvmInline

sealed interface ResourceIdentifier {
    @JvmInline
    value class IdString(val key: String): ResourceIdentifier

    @JvmInline
    value class IdInt(val key: Int): ResourceIdentifier
}

object DummyResources: StringResources {
    override fun getString(id: ResourceIdentifier): String {
        TODO("Not yet implemented")
    }

    override fun getString(id: ResourceIdentifier, vararg params: Any) {
        TODO("Not yet implemented")
    }

}

interface StringResources {
    fun getString(id: ResourceIdentifier): String
    fun getString(id: ResourceIdentifier, vararg params: Any)
}
fun StringResources.getString(id: String): String {
    return ResourceIdentifier.IdString(id).let(::getString)
}
fun StringResources.getString(id: Int): String {
    return ResourceIdentifier.IdInt(id).let(::getString)
}
fun StringResources.getString(id: String, vararg params: Any?): String {
    return ResourceIdentifier.IdString(id).let(::getString)
}
fun StringResources.getString(id: Int, vararg params: Any?): String {
    return ResourceIdentifier.IdInt(id).let(::getString)
}
