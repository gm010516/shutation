package com.example.shutation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shutation.databinding.ActivityAdminMapBinding
import com.example.shutation.databinding.ActivityLoginBinding

class AdminMapActivity : AppCompatActivity() {
    val binding by lazy{ ActivityAdminMapBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}