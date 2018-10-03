package com.kotlin.whoever.common

import com.kotlin.whoever.constants.constants.Companion.requestGoogleLogin
import com.kotlin.whoever.constants.constants.Companion.requestKakaoLogin
import com.kotlin.whoever.constants.constants.Companion.tokenField
import com.kotlin.whoever.model.jsondata.User
import retrofit2.Call
import retrofit2.http.*

// 서버연결을 위한 Path 부분
interface RetrofitService {
    @FormUrlEncoded
    @POST(requestKakaoLogin)
    fun getKakaoAccesToken(
            @Field(tokenField) accessToken: String
    ): Call<User>

    @FormUrlEncoded
    @POST(requestGoogleLogin)
    fun getGoogleAccesToken(
            @Field(tokenField) accessToken: String
    ): Call<User>
}