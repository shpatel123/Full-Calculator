package com.example.basiccalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FullCalculator : AppCompatActivity() {
    private lateinit var inputField: EditText
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullcalculator)

        inputField = findViewById(R.id.numberInput)
        resultText = findViewById(R.id.resultText)

        findViewById<Button>(R.id.btnSwitchToBasic).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        listOf(R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9).forEach { id ->
            findViewById<Button>(id).setOnClickListener { inputField.append((it as Button).text) }
        }

        mapOf(R.id.buttonAdd to "+", R.id.buttonSubtract to "-", R.id.buttonMultiply to "*",
            R.id.buttonDivide to "/").forEach { (id, operator) ->
            findViewById<Button>(id).setOnClickListener { inputField.append(operator) }
        }

        findViewById<Button>(R.id.buttonEqual).setOnClickListener { calculateResult() }
    }

    private fun calculateResult() {
        val expression = inputField.text.toString()
        val result = try {
            evaluateExpression(expression).toString()
        } catch (e: Exception) {
            "Error"
        }
        resultText.text = result
    }

    private fun evaluateExpression(expression: String): Double {
        var modifiedExpression = expression.replace(" ", "")
        var result = 0.0
        var operator = '+'
        var number = ""

        for (i in modifiedExpression.indices) {
            val currentChar = modifiedExpression[i]

            // Accumulate the number (digits or decimals)
            if (currentChar.isDigit() || currentChar == '.') {
                number += currentChar
            }

            // When the operator or the end of the expression is reached
            if (!currentChar.isDigit() && currentChar != '.' || i == modifiedExpression.length - 1) {
                when (operator) {
                    '+' -> result += number.toDouble()
                    '-' -> result -= number.toDouble()
                    '*' -> result *= number.toDouble()
                    '/' -> result /= number.toDouble()
                }
                // Update operator only when it is a valid operator
                if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                    operator = currentChar
                }
                number = ""  // Reset the number for the next number in the expression
            }
        }
        return result
    }

}
