package com.example.shutation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shutation.databinding.ActivityAdminBinding
import com.example.shutation.databinding.ActivityUserBinding

class AdminActivity : AppCompatActivity() {
    val binding by lazy{ ActivityAdminBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.adbus.setOnClickListener{
            val intent= Intent(this,AdminMapActivity::class.java)//정류장
            startActivity(intent)
        }
        binding.adoff.setOnClickListener {
            val intent= Intent(this,AdminGetOffActivity::class.java)//하차 관리
            startActivity(intent)
        }
        binding.adlost.setOnClickListener {
            val intent= Intent(this,LostActivity::class.java)//분실물 확인
            startActivity(intent)
        }
        binding.adcal.setOnClickListener {
            val intent= Intent(this,CalendarActivity::class.java)//일정 관리
            startActivity(intent)
        }
    }
}