package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.zero.setOnClickListener { setTextField("0") }
        binding.one.setOnClickListener { setTextField("1") }
        binding.two.setOnClickListener { setTextField("2") }
        binding.three.setOnClickListener { setTextField("3") }
        binding.four.setOnClickListener { setTextField("4") }
        binding.five.setOnClickListener { setTextField("5") }
        binding.six.setOnClickListener { setTextField("6") }
        binding.seven.setOnClickListener { setTextField("7") }
        binding.eight.setOnClickListener { setTextField("8") }
        binding.nine.setOnClickListener { setTextField("9") }
        binding.dot.setOnClickListener { setTextField(".") }
        binding.plus.setOnClickListener { setTextField("+") }
        binding.minus.setOnClickListener { setTextField("-") }
        binding.multiply.setOnClickListener { setTextField("*") }
        binding.percent.setOnClickListener { setTextField("/") }
        binding.back.setOnClickListener {
            val string = binding.mathOperations.text.toString()
            if (string.isNotEmpty()) {
                binding.mathOperations.text = string.substring(0, string.length - 1)
            }
        }
        binding.leftSquare.setOnClickListener { setTextField("(") }
        binding.rightSquare.setOnClickListener { setTextField(")") }
        binding.ac.setOnClickListener {
            binding.mathOperations.text = ""
        }
        binding.equals.setOnClickListener {
            try {
                val ex = ExpressionBuilder(binding.mathOperations.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if (result == longRes.toDouble())
                    binding.mathResul.text = longRes.toString()
                else
                    binding.mathResul.text = result.toString()

            } catch (e:Exception){
                binding.mathResul.text = "Error"
                Log.d("Error", "${e.message}")
            }
        }
    }

    private fun setTextField(text: String) {
        if(binding.mathResul.text != ""){
            binding.mathOperations.text = binding.mathResul.text
            binding.mathResul.text = ""
        }
        binding.mathOperations.append(text)
    }
}
