package org.notlocalhost.kotlin.extenstions

import kotlin.reflect.KParameter
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

interface DynamicInitializer<T>

inline operator fun <reified C : Any, T : DynamicInitializer<C>> T.invoke(args: Map<String, Any>): C {
    val constructor = C::class.primaryConstructor!!
    val argmap = HashMap<KParameter, Any?>().apply {
        constructor.parameters.forEach { if (it.name in args) put(it, args[it.name]) }
    }
    return constructor.callBy(argmap)
}

inline fun <reified T : Any> T.toMap(prefix: String = ""): Map<String, Any?> {
    val constructor = T::class.primaryConstructor!!
    val properties = T::class.declaredMemberProperties
    val receiver = this
    val argmap = HashMap<String, Any?>().apply {
        constructor.parameters.forEach { field -> this["$prefix${field.name}"] = properties.filter { it.name == field.name }[0].get(receiver)  }
    }
    return argmap
}