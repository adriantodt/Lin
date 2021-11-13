package com.github.adriantodt.lin.ast.node.control

import com.github.adriantodt.lin.ast.node.Expr
import com.github.adriantodt.lin.ast.visitor.NodeMapVisitor
import com.github.adriantodt.lin.ast.visitor.NodeVisitor
import com.github.adriantodt.lin.ast.visitor.NodeVisitor1
import com.github.adriantodt.lin.ast.visitor.NodeVisitorR
import com.github.adriantodt.tartar.api.lexer.Section

data class IfExpr(
    val condition: Expr,
    val thenBranch: Expr,
    val elseBranch: Expr,
    override val section: Section? = null
) : Expr {
    /* @automation(ast.impl IfExpr,Expr)-start */
    override fun accept(visitor: NodeVisitor) = visitor.visitIfExpr(this)

    override fun accept(visitor: NodeMapVisitor): Expr = visitor.visitIfExpr(this)

    override fun <R> accept(visitor: NodeVisitorR<R>): R = visitor.visitIfExpr(this)

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) = visitor.visitIfExpr(this, param0)
    /* @automation-end */
}
