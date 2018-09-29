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
import kotlinx.android.synthetic.main.activity_login.*


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
        }
    }
}



