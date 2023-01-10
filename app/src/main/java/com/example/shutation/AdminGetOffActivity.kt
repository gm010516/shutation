package com.example.shutation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shutation.databinding.ActivityAdminGetOffBinding
import com.example.shutation.model.GoR
import com.google.firebase.firestore.FirebaseFirestore


class AdminGetOffActivity : AppCompatActivity() {
    val binding by lazy { ActivityAdminGetOffBinding.inflate(layoutInflater) }
    var firestore: FirebaseFirestore? = null
    //var auth: FirebaseAuth? = null
    //var database = FirebaseDatabase.getInstance().getReference("busstop2")

    // <item>-선택 안함-</item>
//        <item>사직동 주민센터</item>
//        <item>경복궁역 1번 출구</item>
//        <item>광화문역 9번 출구</item>
//        <item>종각역 4번 출구</item>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()

        var goRs1: ArrayList<GoR> = arrayListOf()
        var goRs2: ArrayList<GoR> = arrayListOf()
        var goRs3: ArrayList<GoR> = arrayListOf()
        var goRs4: ArrayList<GoR> = arrayListOf()
        var fileId: ArrayList<GoR> = arrayListOf()
        goRs1.clear()
        goRs2.clear()
        goRs3.clear()
        goRs4.clear()
        firestore?.collection("busstop1")
            ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null) return@addSnapshotListener

                for (snapshot in querySnapshot.documents)
                    goRs1.add(snapshot.toObject(GoR::class.java)!!)
                binding.off1.text = goRs1.size.toString()

            }
        firestore?.collection("busstop2")
            ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null) return@addSnapshotListener

                for (snapshot in querySnapshot.documents)
                    goRs2.add(snapshot.toObject(GoR::class.java)!!)

                binding.off2.text = goRs2.size.toString()

            }
        firestore?.collection("busstop3")
            ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null) return@addSnapshotListener

                for (snapshot in querySnapshot.documents)
                    goRs3.add(snapshot.toObject(GoR::class.java)!!)

                binding.off3.text = goRs3.size.toString()

            }
        firestore?.collection("busstop4")
            ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null) return@addSnapshotListener

                for (snapshot in querySnapshot.documents)
                    goRs4.add(snapshot.toObject(GoR::class.java)!!)

                binding.off4.text = goRs4.size.toString()

            }
        //var bus1=myRef.child("사직동 주민센터").child("offcount").get().result.toString()
        //binding.off1.text=bus1
        binding.btnReset.setOnClickListener {
            for(i in 0..goRs1.size-1 step(1)){
                firestore?.collection("busstop1")?.document(goRs1[i].userId.toString())?.delete()
            }
            for(j in 0..goRs2.size-1 step(1)){
                firestore?.collection("busstop2")?.document(goRs2[j].userId.toString())?.delete()
            }
            for(k in 0..goRs3.size-1 step(1)){
                firestore?.collection("busstop3")?.document(goRs3[k].userId.toString())?.delete()
            }
            for(d in 0..goRs4.size-1 step(1)){
                firestore?.collection("busstop4")?.document(goRs4[d].userId.toString())?.delete()
            }
                Toast.makeText(this, "초기화완료", Toast.LENGTH_LONG).show()
            goRs1.clear()
            goRs2.clear()
            goRs3.clear()
            goRs4.clear()
            finish() //인텐트 종료
            overridePendingTransition(0, 0) //인텐트 효과 없애기
            val intent = intent //인텐트
            startActivity(intent) //액티비티 열기
            overridePendingTransition(0, 0) //인텐트 효과 없애기
        }



    }

}


