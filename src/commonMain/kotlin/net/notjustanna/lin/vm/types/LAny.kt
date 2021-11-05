package net.notjustanna.lin.vm.types

sealed class LAny {
    abstract fun truth(): Boolean

    abstract val linType: String

    abstract fun getMember(name: String): LAny?

    companion object {
        fun of(value: Any?): LAny {
            return when (value) {
                null, is Unit -> LNull
                true -> LTrue
                false -> LFalse
                is String -> LString(value)
                is Char -> LString(value.toString())
                is Number -> if (value is Float || value is Double) {
                    LDecimal(value.toDouble())
                } else {
                    LInteger(value.toLong())
                }
                is List<*> -> LArray(value.mapTo(mutableListOf()) { of(it) })
                is Map<*, *> -> LObject(value.entries.associateTo(mutableMapOf()) { of(it.key) to of(it.value) })
                else -> throw IllegalArgumentException("Can't convert $value to LAny.")
            }
        }

        fun ofBoolean(value: Boolean): LAny {
            return if (value) LTrue else LFalse
        }

        fun ofEntry(entry: Map.Entry<LAny, LAny>): LAny {
            return LObject(LString("key") to entry.key, LString("value") to entry.value)
        }
    }
}