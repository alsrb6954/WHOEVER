package com.kotlin.whoever.common

import android.app.Application
import android.content.Context
import com.kakao.auth.*

class GlobalApplication:Application() {
    companion object {
        private var obj: GlobalApplication? = null
        lateinit var context: Context

        fun getGlobalApplicationContext(): GlobalApplication {
            return obj!!
        }

    }

    private inner class KakaoSDKAadpter: KakaoAdapter(){
        override fun getSessionConfig() = object : ISessionConfig {
                override fun isSaveFormData() =true
                override fun getAuthTypes() = arrayOf(AuthType.KAKAO_LOGIN_ALL)
                override fun isSecureMode() = false
                override fun getApprovalType() = ApprovalType.INDIVIDUAL
                override fun isUsingWebviewTimer() =false
        }
        override fun getApplicationConfig() = IApplicationConfig { getGlobalApplicationContext() }
    }
    override fun onCreate() {
        super.onCreate()
        obj = this
        context = this
        KakaoSDK.init(KakaoSDKAadpter())
    }
}