package com.example.shutation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shutation.databinding.ActivityLostBinding
import com.example.shutation.placeholder.lostuser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore

class LostActivity : AppCompatActivity() {
    val binding by lazy{ ActivityLostBinding.inflate(layoutInflater) }
    var firestore: FirebaseFirestore? = null
    lateinit var dao: UserDao

    lateinit var adapter: LostAdapter

    lateinit var lostList: ArrayList<lostuser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var auth : FirebaseAuth? = null

            binding.btnlostreg.setOnClickListener {
                val intent= Intent(this,LostregisActivity::class.java)
                startActivity(intent)
            }

        lostList= ArrayList()
        dao= UserDao()
        adapter= LostAdapter(this, lostList)
        binding.recyclerview.layoutManager=LinearLayoutManager(this)
        binding.recyclerview.adapter=adapter
        //사용자 정보 가져오기
        getLostList()
    }
    private fun getLostList() {
        dao.getLostList()?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnapshot in snapshot.children){
                    val lostuser=dataSnapshot.getValue(lostuser::class.java)
                    val key=dataSnapshot.key
                    lostuser?.lostuserKey=key.toString()
                    if(lostuser !=null){
                        lostList.add(lostuser)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}
