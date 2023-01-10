package com.example.shutation.placeholder

import android.net.Uri

data class lostuser(
    var lostuserKey: String,

    var title: String,
    var explain : String,
    var lostdate:String

){


    constructor():this("","","","")
}
