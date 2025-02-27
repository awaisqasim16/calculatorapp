package com.example.calculatorapp
import androidx.appcompat.widget.Toolbar

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var tvDisplay: TextView
    private var currentInput = ""
    private var operator: String? = null
    private var firstOperand: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set a title for the toolbar
        tvDisplay = findViewById(R.id.tvDisplay)
        supportActionBar?.title = "Calculator"
    }

    fun onButtonClick(view: android.view.View) {
        val button = view as Button
        currentInput += button.text
        tvDisplay.text = currentInput
    }

    fun onOperatorClick(view: android.view.View) {
        val button = view as Button
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toDoubleOrNull()
            operator = button.text.toString()
            currentInput = ""
        }
    }

    fun onEqualClick(view: android.view.View) {
        val secondOperand = currentInput.toDoubleOrNull()
        if (firstOperand != null && secondOperand != null && operator != null) {
            val result = when (operator) {
                "+" -> firstOperand!! + secondOperand
                "−" -> firstOperand!! - secondOperand
                "×" -> firstOperand!! * secondOperand
                "÷" -> if (secondOperand != 0.0) firstOperand!! / secondOperand else "Error"
                else -> "Error"
            }
            tvDisplay.text = result.toString()
            currentInput = result.toString()
            firstOperand = null
            operator = null
        }
    }

    fun onClearClick(view: android.view.View) {
        currentInput = ""
        firstOperand = null
        operator = null
        tvDisplay.text = "0"
    }

    fun onSquareClick(view: android.view.View) {
        val value = currentInput.toDoubleOrNull()
        if (value != null) {
            val result = value * value
            tvDisplay.text = result.toString()
            currentInput = result.toString()
        }
    }

    fun onSquareRootClick(view: android.view.View) {
        val value = currentInput.toDoubleOrNull()
        if (value != null && value >= 0) {
            val result = sqrt(value)
            tvDisplay.text = result.toString()
            currentInput = result.toString()
        } else {
            tvDisplay.text = "Error"
        }
    }
}
