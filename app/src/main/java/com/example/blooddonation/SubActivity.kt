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
    }
}