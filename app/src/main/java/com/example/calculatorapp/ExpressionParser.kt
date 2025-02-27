package com.example.calculator

import java.util.*

class ExpressionParser {
    fun parse(expression: String): Double {
        val tokens = expression.toCharArray()
        val values = Stack<Double>()
        val ops = Stack<Char>()

        var i = 0
        while (i < tokens.size) {
            when {
                tokens[i].isDigit() -> {
                    val sb = StringBuilder()
                    while (i < tokens.size && (tokens[i].isDigit() || tokens[i] == '.')) {
                        sb.append(tokens[i++])
                    }
                    values.push(sb.toString().toDouble())
                    i--
                }
                tokens[i] in setOf('+', '-', '*', '/') -> {
                    while (ops.isNotEmpty() && precedence(ops.peek()) >= precedence(tokens[i])) {
                        values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                    }
                    ops.push(tokens[i])
                }
            }
            i++
        }

        while (ops.isNotEmpty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()))
        }

        return values.pop()
    }

    private fun precedence(op: Char): Int {
        return when (op) {
            '+', '-' -> 1
            '*', '/' -> 2
            else -> -1
        }
    }

    private fun applyOp(op: Char, b: Double, a: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> if (b != 0.0) a / b else throw ArithmeticException("Division by zero")
            else -> 0.0
        }
    }
}
