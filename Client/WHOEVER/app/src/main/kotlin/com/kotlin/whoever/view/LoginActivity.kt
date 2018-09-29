package com.kotlin.whoever.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import com.kotlin.whoever.R
import com.kotlin.whoever.common.provideLogin
import com.kotlin.whoever.model.jsondata.User
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    lateinit var callback:SessionCallback
    var kakaoAccessToken:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        callback = SessionCallback()
        btn_kakao_login.setOnClickListener {
            kakaoLogin()
        }
    }

    fun kakaoLogin() {
        Session.getCurrentSession().apply {
            addCallback(callback)
            open(AuthType.KAKAO_LOGIN_ALL, this@LoginActivity)
        }
    }
    inner class SessionCallback: ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?){}
        override fun onSessionOpened() {
            kakaoAccessToken = Session.getCurrentSession().accessToken



            // 비동기식 데이터 주고 받는 서버 부분(나중에 viewModel로 뺀다)
            val call= provideLogin().getAccesToken(kakaoAccessToken.toString())
            call.enqueue(object : Callback<User>{
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("hoho", "fail")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user = response.body()
                    if(user != null) {
                        Log.d("hoho", user!!.wUser_email)
                        startActivity<MainActivity>()
                    }else{
                        Log.d("hoho", "씨발!!!")
                    }
                }
            })
        }
    }


}



