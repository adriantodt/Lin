package net.notjustanna.lin.parser

import net.notjustanna.lin.ast.*
import net.notjustanna.lin.lexer.TokenType
import net.notjustanna.tartar.api.parser.SyntaxException
import net.notjustanna.tartar.createParser
import io.github.cafeteriaguild.lin.parser.utils.matchAll

val linStdParser = createParser(linStdGrammar) {
    val start = peek()
    val list = mutableListOf<Node>()
    matchAll(TokenType.NL, TokenType.SEMICOLON)
    val expr = try {
        do {
            list += parseExpression()
        } while (matchAll(TokenType.NL, TokenType.SEMICOLON) && !eof)

        if (list.isNotEmpty() && list.last() is Expr) {
            val last = list.removeLast() as Expr
            MultiExpr(list, last, start.section)
        } else
            MultiNode(list, start.section)

    } catch (e: Exception) {
        InvalidNode {
            section(start.section)
            child(*list.toTypedArray())
            error(e)
        }
    }

    if (!eof) InvalidNode {
        child(expr)
        error(SyntaxException("Should've reached end of content", eat().section))
    } else expr
}
