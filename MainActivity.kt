package com.example.basiccalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Getting references to UI components
        val num1EditText = findViewById<EditText>(R.id.num1)
        val num2EditText = findViewById<EditText>(R.id.num2)
        val resultTextView = findViewById<TextView>(R.id.resultText)

        val addButton = findViewById<Button>(R.id.addButton)
        val subButton = findViewById<Button>(R.id.subButton)
        val mulButton = findViewById<Button>(R.id.mulButton)
        val divButton = findViewById<Button>(R.id.divButton)
        val ansButton = findViewById<Button>(R.id.ansButton)
        val fullCalcButton = findViewById<Button>(R.id.fullCalcButton)

        var result: Double = 0.0
        var selectedOperation: String = ""

        // Function to perform calculation
        fun calculate() {
            val num1 = num1EditText.text.toString().toDoubleOrNull()
            val num2 = num2EditText.text.toString().toDoubleOrNull()

            if (num1 == null || num2 == null) {
                resultTextView.text = "Result: Invalid Input"
                return
            }

            result = when (selectedOperation) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
                else -> 0.0
            }
        }

        // Button Click Listeners
        addButton.setOnClickListener {
            selectedOperation = "+"
        }
        subButton.setOnClickListener {
            selectedOperation = "-"
        }
        mulButton.setOnClickListener {
            selectedOperation = "*"
        }
        divButton.setOnClickListener {
            selectedOperation = "/"
        }

        // Answer Button Click - Perform Calculation
        ansButton.setOnClickListener {
            calculate()
            resultTextView.text = "Result: $result"
        }

        // Full Calculator Button (Future implementation)
        fullCalcButton.setOnClickListener {
            val intent = Intent(this,FullCalculator::class.java)
            startActivity(intent)
        }
    }
}
