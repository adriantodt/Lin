package net.notjustanna.lin.parser

import net.notjustanna.lin.ast.BinaryOperationType
import net.notjustanna.lin.ast.Node
import net.notjustanna.lin.ast.UnaryOperationType
import net.notjustanna.lin.lexer.TokenType
import net.notjustanna.lin.lexer.TokenType.*
import net.notjustanna.lin.parser.parselets.operations.BinaryOperatorParser
import net.notjustanna.lin.parser.parselets.operations.UnaryOperatorParser
import net.notjustanna.lin.parser.parselets.value.*
import net.notjustanna.tartar.createGrammar

val linStdGrammar = createGrammar<TokenType, Node> {
    // Expressions
    prefix(INT, IntParser)
    prefix(LONG, LongParser)
    prefix(FLOAT, FloatParser)
    prefix(DOUBLE, DoubleParser)
    prefix(NULL, NullParser)
    prefix(UNIT, UnitParser)
    prefix(CHAR, CharParser)
    prefix(STRING, StringParser)
    prefix(TRUE, BooleanParser(true))
    prefix(FALSE, BooleanParser(false))

    // Unary operations
    prefix(BANG, UnaryOperatorParser(UnaryOperationType.NOT))
    prefix(PLUS, UnaryOperatorParser(UnaryOperationType.POSITIVE))
    prefix(MINUS, UnaryOperatorParser(UnaryOperationType.NEGATIVE))

    // Binary operations
    infix(EQ, BinaryOperatorParser(Precedence.EQUALITY, BinaryOperationType.EQUALS))
    infix(NEQ, BinaryOperatorParser(Precedence.EQUALITY, BinaryOperationType.NOT_EQUALS))
    infix(PLUS, BinaryOperatorParser(Precedence.ADDITIVE, BinaryOperationType.ADD))
    infix(MINUS, BinaryOperatorParser(Precedence.ADDITIVE, BinaryOperationType.SUBTRACT))
    infix(ASTERISK, BinaryOperatorParser(Precedence.MULTIPLICATIVE, BinaryOperationType.MULTIPLY))
    infix(SLASH, BinaryOperatorParser(Precedence.MULTIPLICATIVE, BinaryOperationType.DIVIDE))
    infix(REM, BinaryOperatorParser(Precedence.MULTIPLICATIVE, BinaryOperationType.REMAINING))
    infix(AND, BinaryOperatorParser(Precedence.CONJUNCTION, BinaryOperationType.AND))
    infix(OR, BinaryOperatorParser(Precedence.DISJUNCTION, BinaryOperationType.OR))
    infix(LT, BinaryOperatorParser(Precedence.COMPARISON, BinaryOperationType.LT))
    infix(LTE, BinaryOperatorParser(Precedence.COMPARISON, BinaryOperationType.LTE))
    infix(GT, BinaryOperatorParser(Precedence.COMPARISON, BinaryOperationType.GT))
    infix(GTE, BinaryOperatorParser(Precedence.COMPARISON, BinaryOperationType.GTE))
    infix(ELVIS, BinaryOperatorParser(Precedence.ELVIS, BinaryOperationType.ELVIS))
    infix(IN, BinaryOperatorParser(Precedence.NAMED_CHECKS, BinaryOperationType.IN))
    infix(RANGE, BinaryOperatorParser(Precedence.RANGE, BinaryOperationType.RANGE))

    //TODO Increment/decrement

    //TODO Assign operations
}
