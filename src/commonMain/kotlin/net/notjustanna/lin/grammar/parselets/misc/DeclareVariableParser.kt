package net.notjustanna.lin.grammar.parselets.misc

import net.notjustanna.lin.ast.node.Expr
import net.notjustanna.lin.ast.node.InvalidNode
import net.notjustanna.lin.ast.node.Node
import net.notjustanna.lin.ast.node.declare.DeclareVariableNode
import net.notjustanna.lin.grammar.utils.skipOnlyUntil
import net.notjustanna.lin.lexer.TokenType
import net.notjustanna.tartar.api.parser.ParserContext
import net.notjustanna.tartar.api.parser.PrefixParser
import net.notjustanna.tartar.api.parser.SyntaxException
import net.notjustanna.tartar.api.parser.Token
import net.notjustanna.lin.parser.utils.matchAll

class DeclareVariableParser(val mutable: Boolean) : PrefixParser<TokenType, Node> {
    override fun parse(ctx: ParserContext<TokenType, Node>, token: Token<TokenType>): Node {
        // TODO Variable destructuring goes here (Check Lin/old)
        val ident = ctx.eat(TokenType.IDENTIFIER)
        ctx.skipOnlyUntil(TokenType.ASSIGN)
        if (ctx.match(TokenType.ASSIGN)) {
            ctx.matchAll(TokenType.NL)
            val expr = ctx.parseExpression().let {
                it as? Expr ?: return InvalidNode {
                    section(it.section)
                    child(it)
                    error(SyntaxException("Expected an expression", it.section))
                }
            }
            return DeclareVariableNode(ident.value, mutable, expr, token.section)
        }
        // Variable delegation goes here (Check Lin/old)

        return DeclareVariableNode(ident.value, mutable, null, token.section)
    }
}
