package com.kotlin.whoever.view.content.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.whoever.view.content.login.LoginActivity
import org.jetbrains.anko.startActivity

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity<LoginActivity>()
    }
}