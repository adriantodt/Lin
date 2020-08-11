package io.github.cafeteriaguild.lin.rt.lib.lang

import io.github.cafeteriaguild.lin.rt.exceptions.LinNullException
import io.github.cafeteriaguild.lin.rt.lib.LObj
import io.github.cafeteriaguild.lin.rt.lib.nativelang.properties.Property

object LNull : LObj {
    override fun propertyOf(name: String): Property {
        throw LinNullException()
    }

    override fun toString() = "null"
}