package com.kotlin.whoever.model.jsondata

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("wUser_id") val wUser_id:String, @SerializedName("wUser_name") val wUser_name:String,
                @SerializedName("wUser_email") val wUser_email:String, @SerializedName("wUser_profileImageUrl") val wUser_profileImageUrl:String)