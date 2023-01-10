package com.example.shutation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shutation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    val binding by lazy{ ActivityUserBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnbusroute.setOnClickListener{
            val intent= Intent(this,MapActivity::class.java)//버스 정류
            startActivity(intent)
        }
        binding.btnlost.setOnClickListener{
            val intent= Intent(this,LostActivity::class.java)//분실물
            startActivity(intent)
        }
        binding.btncal.setOnClickListener{
            val intent= Intent(this,CalendarActivity::class.java)//개인 일정
            startActivity(intent)
        }

        binding.btnoff.setOnClickListener{
            val intent=Intent(this,GetOffActivity::class.java)//하차신청
            startActivity(intent)
        }

    }
}