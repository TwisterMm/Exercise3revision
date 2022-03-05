package com.example.blooddonation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonation.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reset()
        binding.reset.setOnClickListener{ reset() }
        binding.submit.setOnClickListener { submit() }
    }

    private fun submit() {
        val weight = binding.editWeight.text.toString().toDoubleOrNull() ?: 0.0
        val height = binding.editHeight.text.toString().toDoubleOrNull() ?: 0.0
        val condition = getCondition()
        val firstTime = binding.checkBox.isChecked
        val age = binding.editAge.text.toString().toIntOrNull() ?:0

        //validation
        // Basic input validation ------------------------------------------------------------------
        if (weight <= 0.0) {
            toast(getString(R.string.invalid_weight))
            return
        }

        if (height <= 0.0) {
            toast(getString(R.string.invalid_height))
            return
        }

        if (age <= 0) {
            toast(getString(R.string.invalid_age))
            return
        }

        // Calculation -----------------------------------------------------------------------------
        val bmi      = getBmi(weight, height)                         // Double
        val status   = getStatus(bmi)                                 // String
        val eligible = getEligible(status, condition, firstTime, age) // Boolean

        val intent = Intent(this, SubActivity::class.java)
            .putExtra("weight", weight)       // Double
            .putExtra("height", height)       // Double
            .putExtra("bmi", bmi)             // Double
            .putExtra("status", status)       // String
            .putExtra("condition", condition) // String
            .putExtra("firstTime", firstTime) // Boolean
            .putExtra("age", age)             // Int
            .putExtra("eligible", eligible)   // Boolean
        startActivity(intent)
    }

    private fun getEligible(status: String, condition: String, firstTime: Boolean, age: Int): Boolean {
        if(status == getString(R.string.normal) && condition == getString(R.string.healthy)){
            if(firstTime){
                if(age in 18..60) return true
            }else{
                if(age in 18..65) return true
            }
        }
        return false
    }

    private fun getStatus(bmi: Double): String {
        return when {
            bmi < 18.5 -> getString(R.string.underweight)
            bmi < 25.0 -> getString(R.string.normal)
            bmi < 30.0 -> getString(R.string.overweight)
            else -> getString(R.string.obese)
        }
    }

    private fun getBmi(weight: Double, height: Double): Double {
        return weight / height.pow(2)
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun getCondition(): String {
        return when (binding.rgpCondition.checkedRadioButtonId) {
            R.id.radHealthy -> getString(R.string.healthy)
            R.id.radSick    -> getString(R.string.sick)
            else            -> ""
        }
    }

    private fun reset() {
        with(binding){
            editWeight.text.clear()
            editHeight.text.clear()
            rgpCondition.check(R.id.radHealthy)
            checkBox.isChecked = false
            editAge.text.clear()

            editWeight.requestFocus()
        }
    }
}