package com.example.shutation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.shutation.databinding.ActivityMapBinding
import com.google.firebase.auth.FirebaseAuth

class MapActivity : AppCompatActivity() {
    val binding by lazy{ ActivityMapBinding.inflate(layoutInflater) }
    var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
            binding.GetOffPlus.setOnClickListener {
                val intent= Intent(this,GetOffActivity::class.java)
                startActivity(intent)
            }
    }
}