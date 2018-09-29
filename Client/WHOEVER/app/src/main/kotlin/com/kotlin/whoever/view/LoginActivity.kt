package com.kotlin.whoever.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.whoever.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_kakao_login.setOnClickListener {

        }
    }
}
