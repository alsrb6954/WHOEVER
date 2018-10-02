package com.kotlin.whoever.common

import android.content.Context
import com.kotlin.whoever.R
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

// singleton(네트워크 통신을 위한 부분)
private const val URL = "http://175.124.86.194:3000"

fun provideLogin() = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitService::class.java)!!


//private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, authInterceptor:AuthInterceptor?):OkHttpClient{
//    val b = OkHttpClient.Builder()
//    if(null != authInterceptor){
//        b.addInterceptor(authInterceptor)
//    }
//    b.addInterceptor(interceptor)
//    return b.build()
//}
//private fun provideLoggingInterceptor():HttpLoggingInterceptor{
//    val interceptor = HttpLoggingInterceptor()
//    interceptor.level = HttpLoggingInterceptor.Level.BODY
//    return interceptor
//}
//internal class AuthInterceptor(private val token: String): Interceptor {
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val original = chain.request()
//
//        val b = original.newBuilder().addHeader("Authorization", "token " + token)
//        val request = b.build()
//        return chain.proceed(request)
//    }
//}
