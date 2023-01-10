package com.example.shutation.model

import android.net.Uri

data class ContentDTO(
    var title: String?=null,//사진 제목
    var explain : String? = null,//설명
    var imageUrl : String? = null,//이미지 주소
    var uid : String? = null,//이미지 올린 아이디 uid
    var userId : String? = null,//이미지 올린 유저 아이디
    var timestamp : Long? = null,//업로드 시간
    var lostdate:String?=null,
    var imageFileName:String?=null
)
data class GoR(
    var userId : String? = null,
   var busname:String?=null,
   var offcount:String?=null
)
data class Memo(
    var Regimage:String?=null,
    var regTime: Uri?=null,
    var name:String?=null
)