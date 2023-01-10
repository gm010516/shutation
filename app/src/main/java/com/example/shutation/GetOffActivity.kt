package com.example.shutation

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.icu.text.Transliterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.shutation.databinding.ActivityAdminGetOffBinding
import com.example.shutation.databinding.ActivityGetOffBinding
import com.example.shutation.model.ContentDTO
import com.example.shutation.model.GoR
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase

class GetOffActivity : AppCompatActivity() {
    val binding by lazy{ ActivityGetOffBinding.inflate(layoutInflater) }
    var auth : FirebaseAuth? = null
    var firestore=FirebaseFirestore.getInstance()
    var goR=GoR()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        var name=auth?.currentUser?.email

        binding.offcancel.visibility= View.INVISIBLE
        binding.offList.adapter=ArrayAdapter.createFromResource(this,R.array.busList,android.R.layout.simple_spinner_item)
        binding.btngetoff.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("하차 신청")
                .setMessage("이번 정류장에서 하차하시겠습니까?")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                       // goR.uid = auth?.currentUser?.uid
                        var busstopname=binding.offList.selectedItem.toString()
                        if(busstopname=="사직동 주민센터"){
                            binding.resultText.text = busstopname+"에서 하차신청이 되었습니다"
                            //goR.off1Count="True"
                            goR.offcount="True"
                            goR.busname=busstopname
                            goR.userId=name
                            FirebaseFirestore.getInstance().collection("busstop1").document(name.toString()).set(goR)

                        }else if(busstopname=="경복궁역 1번 출구"){
                            binding.resultText.text = busstopname+"에서 하차신청이 되었습니다"
                            goR.offcount="True"
                            goR.busname=busstopname
                            goR.userId=name

                            FirebaseFirestore.getInstance().collection("busstop2").document(name.toString()).set(goR)

                        }else if(busstopname=="광화문역 9번 출구"){
                            binding.resultText.text = busstopname+"에서 하차신청이 되었습니다"
                            goR.offcount="True"
                            goR.busname=busstopname
                            goR.userId=name

                            FirebaseFirestore.getInstance().collection("busstop3").document(name.toString()).set(goR)

                        }else if(busstopname=="종각역 4번 출구"){
                            binding.resultText.text = busstopname+"에서 하차신청이 되었습니다"
                            goR.offcount="True"
                            goR.userId=name
                            goR.busname=busstopname

                            FirebaseFirestore.getInstance().collection("busstop4").document(name.toString()).set(goR)

                        }else {
                            binding.resultText.text = "하차 정류장을 선택해주세요"
                        }

                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->
                        binding.resultText.text = ""
                    })
            // 다이얼로그를 띄워주기
            builder.show()
            Toast.makeText(this@GetOffActivity, "하차 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show()
            binding.btngetoff.visibility=View.INVISIBLE
            binding.offcancel.visibility=View.VISIBLE
            setResult(Activity.RESULT_OK)
        }
        binding.offcancel.setOnClickListener {
            var busstopname=binding.offList.selectedItem.toString()
            if(goR.userId==name){
                if(busstopname=="사직동 주민센터"){
                    firestore?.collection("busstop1")?.document(name.toString())?.delete()
                    binding.resultText.text = busstopname+"에서 하차신청이 취소되었습니다"
                }else if(busstopname=="경복궁역 1번 출구"){
                    firestore?.collection("busstop2")?.document(name.toString())?.delete()
                    binding.resultText.text = busstopname+"에서 하차신청이 취소되었습니다"


                }else if(busstopname=="광화문역 9번 출구"){
                    firestore?.collection("busstop3")?.document(name.toString())?.delete()
                    binding.resultText.text = busstopname+"에서 하차신청이 취소되었습니다"

                }else if(busstopname=="종각역 4번 출구"){
                    firestore?.collection("busstop4")?.document(name.toString())?.delete()
                    binding.resultText.text = busstopname+"에서 하차신청이 취소되었습니다"

                }else {
                    binding.resultText.text = "취소할 정류장을 선택해주세요"
                }            }else{
                Toast.makeText(this@GetOffActivity, "하차 취소가 불가능합니다.", Toast.LENGTH_LONG).show()

            }

        }
    }


}