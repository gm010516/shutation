package com.example.shutation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.shutation.placeholder.lostuser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.File

class LostAdapter (private val context:Context,private val lostList:ArrayList<lostuser>):
RecyclerView.Adapter<LostAdapter.LostViewHolder>(){
  // val storageRef = Firebase.storage.reference
  //  var firebaseStore=FirebaseFirestore.getInstance()
    class  LostViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        //val imageview_content:ImageView=itemView.findViewById(R.id.imageview_content)
        val titleText:TextView=itemView.findViewById(R.id.title_textview)
        val explainText:TextView=itemView.findViewById(R.id.explain_textview)
        val dateText:TextView=itemView.findViewById(R.id.date_textview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LostViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.lostlist,parent,false)
        return LostViewHolder(view)

    }

    override fun onBindViewHolder(holder: LostViewHolder, position: Int) {

        val lostuser: lostuser =lostList[position]


       //holder.imageview_content.setImageURI(lostuser.imageUrl.toUri())
        holder.titleText.text=lostuser.title
        holder.explainText.text=lostuser.explain
        holder.dateText.text=lostuser.lostdate

    }

    override fun getItemCount(): Int {
        return lostList.size

    }
}