package com.example.shutation

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.shutation.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    val binding by lazy{ ActivityRegisterBinding.inflate(layoutInflater) }
    private var auth : FirebaseAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth= Firebase.auth
        binding.registerend.setOnClickListener {
          initSignup()
        }
    }
    private fun initSignup() {//성공!!
        binding.registerend.setOnClickListener {
            val email = binding.registerId.text.toString()
            val password = binding.registerPw.text.toString()

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"회원가입에 성공했습니다!",Toast.LENGTH_SHORT).show()
                        val intent= Intent(this,LoginActivity::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this,"이미 존재하는 계정이거나, 회원가입에 실패했습니다.",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


}