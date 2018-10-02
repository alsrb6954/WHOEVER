package com.kotlin.whoever.common

import com.kotlin.whoever.model.jsondata.User
import retrofit2.Call
import retrofit2.http.*

// 서버연결을 위한 Path 부분
interface RetrofitService {
    @FormUrlEncoded
    @POST("auth/kakao")
    fun getKakaoAccesToken(
            @Field("wAccess_token") accessToken: String
    ): Call<User>

    @FormUrlEncoded
    @POST("auth/google")
    fun getGoogleAccesToken(
            @Field("wAccess_token") accessToken: String
    ): Call<User>
}