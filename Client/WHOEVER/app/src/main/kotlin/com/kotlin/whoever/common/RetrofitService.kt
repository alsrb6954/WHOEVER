package com.kotlin.whoever.common

import com.kotlin.whoever.model.jsondata.User
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @FormUrlEncoded
    @POST("auth/kakao")
    fun getAccesToken(
            @Field("wAccess_token") accessToken: String
    ): Call<User>
}