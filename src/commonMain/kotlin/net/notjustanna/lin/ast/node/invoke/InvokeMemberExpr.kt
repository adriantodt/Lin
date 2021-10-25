package net.notjustanna.lin.ast.node.invoke

import net.notjustanna.lin.ast.node.Expr
import net.notjustanna.lin.ast.visitor.NodeVisitor0
import net.notjustanna.lin.ast.visitor.NodeVisitor0R
import net.notjustanna.lin.ast.visitor.NodeVisitor1
import net.notjustanna.tartar.api.lexer.Section

data class InvokeMemberExpr(
    val target: Expr, val nullSafe: Boolean, val name: String, val arguments: List<Expr>, override val section: Section
) : Expr {
    /* @automation(ast.impl InvokeMemberExpr)-start */
    override fun accept(visitor: NodeVisitor0) = visitor.visitInvokeMemberExpr(this)

    override fun <R> accept(visitor: NodeVisitor0R<R>): R = visitor.visitInvokeMemberExpr(this)

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) = visitor.visitInvokeMemberExpr(this, param0)
    /* @automation-end */
}