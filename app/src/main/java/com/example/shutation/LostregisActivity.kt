package com.example.shutation

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.example.shutation.databinding.ActivityLostregisBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat
import java.util.*
import com.example.shutation.model.ContentDTO
import com.example.shutation.placeholder.lostuser

@Suppress("DEPRECATION")
class LostregisActivity : AppCompatActivity() {
    val binding by lazy{ ActivityLostregisBinding.inflate(layoutInflater) }

    var PICK_IMAGE_FROM_ALBUM = 0
    var storage : FirebaseStorage? = null
    var photoUri : Uri? = null
    var auth : FirebaseAuth? = null
    var firestore : FirebaseFirestore? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()


        //binding.uploadimg.setOnClickListener {
        //    var photoPickerIntent = Intent(Intent.ACTION_PICK)
        //    photoPickerIntent.type = "image/*"
         //   startActivityForResult(photoPickerIntent,PICK_IMAGE_FROM_ALBUM)
        //}
        val dao = UserDao()
        binding.loston.setOnClickListener {

        }
        binding.btnDate.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE)
            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    binding.DateText.setText("${year}.${month+1}.${dayOfMonth}")
                }
            }, year, month, date)
            dlg.show()
        }
        binding.lostback.setOnClickListener{
        val intent= Intent(this,LostActivity::class.java)
        startActivity(intent)
        }
        binding.loston.setOnClickListener{
        //데이터베이스에 분실물정보를 넣는 코드 입력

                val title = binding.losttitle.text.toString()
                val explain = binding.lostmain.text.toString()
                var lostdate=binding.DateText.text.toString()
                //gs://bus-baewha.appspot.com/images/202031002@baewha.ac.kr
            val lostuser = lostuser("", title, explain,lostdate)

            dao.add(lostuser)?.addOnSuccessListener {
                Toast.makeText(this, "등록 성공", Toast.LENGTH_SHORT).show()
            }?.addOnFailureListener {
                Toast.makeText(this, "등록 실패: ${it.message}", Toast.LENGTH_SHORT).show()
            }
            val intent: Intent = Intent(this@LostregisActivity, LostActivity::class.java)
            startActivity(intent)
            }
        }



}




