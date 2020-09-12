package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastSeenMember = "na"
    var canPutDot = true
    var isOperatorPressed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun digitPressed(view: View){
        tvDisplay.append((view as Button).text)
        lastSeenMember = "num"

    }
    fun operatorPressed(view: View){
        if((! (tvDisplay.text.contains("+") ||
                    tvDisplay.text.contains("-") ||
                    tvDisplay.text.contains("*") ||
                    tvDisplay.text.contains("/"))) ||
            (tvDisplay.text.startsWith("-") && (! (tvDisplay.text.substring(1, tvDisplay.text.length).contains("+") ||
                    tvDisplay.text.substring(1, tvDisplay.text.length).contains("-") ||
                    tvDisplay.text.substring(1, tvDisplay.text.length).contains("*") ||
                    tvDisplay.text.substring(1, tvDisplay.text.length).contains("/"))))){
            if(lastSeenMember == "num"){
                tvDisplay.append((view as Button).text)
                lastSeenMember = "op"
                isOperatorPressed = true
                canPutDot = true
            }
        }

    }
    fun dotPressed(view: View){
        if(lastSeenMember == "num" && canPutDot){
            canPutDot = false
            tvDisplay.append(".")
            lastSeenMember = "dot"
        }
    }
    fun calculate(view: View){
        var str = tvDisplay.text.toString()
        println(str)
        try {
            if (!str.startsWith("-")) {
                when {
                    str.contains("+") -> {
                        val firstNumber = str.split("+")[0].toDouble()
                        val secondNumber = str.split("+")[1].toDouble()
                        tvDisplay.text = ""
                        tvDisplay.text =
                            formatTextBeforeDisplay((firstNumber + secondNumber).toString())

                    }
                    str.contains("-") -> {
                        val firstNumber = str.split("-")[0].toDouble()
                        val secondNumber = str.split("-")[1].toDouble()
                        tvDisplay.text = ""
                        tvDisplay.text =
                            formatTextBeforeDisplay((firstNumber - secondNumber).toString())

                    }
                    str.contains("*") -> {
                        val firstNumber = str.split("*")[0].toDouble()
                        val secondNumber = str.split("*")[1].toDouble()
                        tvDisplay.text = ""
                        tvDisplay.text =
                            formatTextBeforeDisplay((firstNumber * secondNumber).toString())

                    }
                    str.contains("/") -> {
                        val firstNumber = str.split("/")[0].toDouble()
                        val secondNumber = str.split("/")[1].toDouble()
                        tvDisplay.text = ""
                        tvDisplay.text =
                            formatTextBeforeDisplay((firstNumber / secondNumber).toString())

                    }

                }
            } else{
                str = str.substring(1, str.length)
                when {
                    str.contains("+") -> {
                        val firstNumber = str.split("+")[0].toDouble()
                        val secondNumber = str.split("+")[1].toDouble()
                        tvDisplay.text = ""
                        tvDisplay.text =
                            formatTextBeforeDisplay((-1 * firstNumber + secondNumber).toString())

                    }
                    str.contains("-") -> {
                        val firstNumber = str.split("-")[0].toDouble()
                        val secondNumber = str.split("-")[1].toDouble()
                        tvDisplay.text = ""
                        tvDisplay.text =
                            formatTextBeforeDisplay((-1 * firstNumber - secondNumber).toString())

                    }
                    str.contains("*") -> {
                        val firstNumber = str.split("*")[0].toDouble()
                        val secondNumber = str.split("*")[1].toDouble()
                        tvDisplay.text = ""
                        tvDisplay.text =
                            formatTextBeforeDisplay((-1 * firstNumber * secondNumber).toString())

                    }
                    str.contains("/") -> {
                        val firstNumber = str.split("/")[0].toDouble()
                        val secondNumber = str.split("/")[1].toDouble()
                        tvDisplay.text = ""
                        tvDisplay.text =
                            formatTextBeforeDisplay((-1 * firstNumber / secondNumber).toString())

                    }

                }
            }
        } catch (e: ArithmeticException){
            print(e.printStackTrace())
            tvDisplay.text = "Arithmetic Exception"
        }
    }
    private fun formatTextBeforeDisplay(str: String): String {
        return if(str.contains(".0")){
            str.substring(0, str.indexOf("."))
        }else{
            str
        }
    }

    fun clearAll(view: View){
        tvDisplay.text = ""
    }
}