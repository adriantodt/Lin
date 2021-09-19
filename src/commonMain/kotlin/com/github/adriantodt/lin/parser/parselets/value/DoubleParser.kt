package com.github.adriantodt.lin.parser.parselets.value

import com.github.adriantodt.lin.ast.DoubleExpr
import com.github.adriantodt.lin.ast.Node
import com.github.adriantodt.lin.lexer.TokenType
import com.github.adriantodt.lin.parser.utils.maybeIgnoreNL
import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.Token

object DoubleParser : PrefixParser<TokenType, Node> {
    override fun parse(ctx: ParserContext<TokenType, Node>, token: Token<TokenType>): Node {
        ctx.maybeIgnoreNL()
        return DoubleExpr(token.value.toDouble(), token.section)
    }
}
