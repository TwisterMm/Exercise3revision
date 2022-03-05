package com.example.blooddonation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blooddonation.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { finish() }

        val weight    = intent.getDoubleExtra("weight", 0.0)
        val height    = intent.getDoubleExtra("height", 0.0)
        val bmi       = intent.getDoubleExtra("bmi", 0.0)
        val status    = intent.getStringExtra("status") ?: ""
        val condition = intent.getStringExtra("condition") ?: ""
        val firstTime = intent.getBooleanExtra("firstTime", false)
        val age       = intent.getIntExtra("age", 0)
        val eligible  = intent.getBooleanExtra("eligible", false)

        with(binding){
            outtWeight.text = "%.2f".format(weight)
            outHeight.text = "%.2f".format(height)
            edtBMI.text = "%.2f".format(bmi)
            outAge.text = age.toString()
            edtStatus.text = status
            outCon.text = condition
            edtFTD.text = if(firstTime) "Yes" else "No"

            if(eligible){
                outEligible.text = "Eligible"
                outEligible.setTextColor(getColor(R.color.teal_200))
            }else{
                outEligible.text = "Not Eligible"
                outEligible.setTextColor(getColor(R.color.red))
            }
        }
    }
}