package com.example.shutation

import com.example.shutation.placeholder.lostuser
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

class UserDao {
    private var databaseReference:DatabaseReference?=null

    init{
        val db=FirebaseDatabase.getInstance()
        databaseReference=db.getReference("images")
    }

    fun add(lostuser: lostuser?): Task<Void>{
        return databaseReference!!.push().setValue(lostuser)
    }

    fun getLostList(): Query?{
        return databaseReference
    }
}