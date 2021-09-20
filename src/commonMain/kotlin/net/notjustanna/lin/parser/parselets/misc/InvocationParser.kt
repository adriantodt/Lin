package net.notjustanna.lin.parser.parselets.misc

import net.notjustanna.lin.ast.*
import net.notjustanna.lin.lexer.TokenType
import net.notjustanna.lin.parser.Precedence
import net.notjustanna.lin.parser.utils.maybeIgnoreNL
import net.notjustanna.tartar.api.parser.InfixParser
import net.notjustanna.tartar.api.parser.ParserContext
import net.notjustanna.tartar.api.parser.SyntaxException
import net.notjustanna.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.parser.utils.matchAll
object InvocationParser : InfixParser<TokenType, Node> {
    override val precedence: Int = Precedence.POSTFIX

    override fun parse(ctx: ParserContext<TokenType, Node>, left: Node, token: Token<TokenType>): Node {
        if (left !is Expr) {
            return InvalidNode {
                section(token.section)
                child(left)
                error(SyntaxException("Expected an expression", left.section))
            }
        }
        val arguments = mutableListOf<Expr>()

        ctx.matchAll(TokenType.NL)
        if (!ctx.match(TokenType.R_PAREN)) {
            do {
                //TODO Implement Spread Operator
                ctx.matchAll(TokenType.NL)
                arguments += ctx.parseExpression().let {
                    it as? Expr ?: return InvalidNode {
                        section(token.section)
                        child(it)
                        error(SyntaxException("Expected an expression", it.section))
                    }
                }
                ctx.matchAll(TokenType.NL)
            } while (ctx.match(TokenType.COMMA))
            ctx.eat(TokenType.R_PAREN)
        }
        // Last-parameter Lambda goes here (Check Lin/old)

        ctx.maybeIgnoreNL()

        if (left is PropertyAccessExpr) {
            return InvokeMemberExpr(left.target, left.nullSafe, left.name, arguments, token.section)
        } else if (left is IdentifierExpr) {
            return InvokeLocalExpr(left.name, arguments, token.section)
        }

        return InvokeExpr(left, arguments, token.section)
    }
}
