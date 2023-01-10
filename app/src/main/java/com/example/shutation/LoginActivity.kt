package com.example.shutation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.shutation.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    val binding by lazy{ ActivityLoginBinding.inflate(layoutInflater) }
    private var auth : FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth= Firebase.auth
        binding.btnLogin.setOnClickListener {

            initLogin()
            Log.d("login","로그인 버튼 클릭")

        }
        binding.btnRegister.setOnClickListener{
            val intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }
    private fun initLogin() {//성공!!
        binding.btnLogin.setOnClickListener {
            val email = binding.InputID.text.toString()
            val password = binding.InputPw.text.toString()

            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        if(email=="admin@baewha.ac.kr") {
                            Toast.makeText(this, "관리자 로그인 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, AdminActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, UserActivity::class.java)
                            startActivity(intent)
                        }
                    }else {
                        Toast.makeText(this,"아이디와 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }

    /*fun email_login(){
        Log.d("login","email_login완료")
        var id=binding.InputID
        var pwd=binding.InputPw
        if(id.text.toString().isNullOrEmpty()||pwd.text.toString().isNullOrEmpty()){
            Toast.makeText(this,"이메일 혹은 비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show()
        }else{
            signinEmail()
        }
    }
    fun signinEmail() {
        auth?.signInWithEmailAndPassword(binding.InputID.text.toString(),binding.InputPw.text.toString())
            ?.addOnCompleteListener {
                    task ->
                if(task.isSuccessful) {
                    // Login, 아이디와 패스워드가 맞았을 때
                    moveMainPage(task.result?.user)
                } else {
                    // Show the error message, 아이디와 패스워드가 틀렸을 때
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }
    fun moveMainPage(user:FirebaseUser?){
        if(user!=null){
            val intent= Intent(this,UserActivity::class.java)
            startActivity(intent)
            Log.d("login","로그인 완료")
        }
    }
*/
}
