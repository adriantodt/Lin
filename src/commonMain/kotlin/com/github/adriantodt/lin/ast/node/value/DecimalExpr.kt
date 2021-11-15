package com.github.adriantodt.lin.ast.node.value

import com.github.adriantodt.lin.ast.node.ConstExpr
import com.github.adriantodt.lin.ast.node.Expr
import com.github.adriantodt.lin.ast.visitor.NodeMapVisitor
import com.github.adriantodt.lin.ast.visitor.NodeVisitor
import com.github.adriantodt.lin.ast.visitor.NodeVisitor1
import com.github.adriantodt.lin.ast.visitor.NodeVisitorR
import com.github.adriantodt.tartar.api.lexer.Section

public data class DecimalExpr(val value: Double, override val section: Section? = null) : ConstExpr {
    /* @automation(ast.impl DecimalExpr,Expr)-start */
    override fun accept(visitor: NodeVisitor) {
        visitor.visitDecimalExpr(this)
    }

    override fun accept(visitor: NodeMapVisitor): Expr {
        return visitor.visitDecimalExpr(this)
    }

    override fun <R> accept(visitor: NodeVisitorR<R>): R {
        return visitor.visitDecimalExpr(this)
    }

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) {
        visitor.visitDecimalExpr(this, param0)
    }
    /* @automation-end */
}
