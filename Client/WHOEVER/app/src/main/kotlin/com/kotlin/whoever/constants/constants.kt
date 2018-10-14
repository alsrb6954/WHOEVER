package com.kotlin.whoever.constants

class constants {
    companion object{
        const val RC_SIGN_IN = 1000
        const val URL = "http://211.211.204.120:3000"
        const val tokenField = "wAccess_token"
        const val requestKakaoLogin = "auth/kakao"
        const val requestGoogleLogin = "auth/google"
        const val loginErrorMeassage = "Unexpected error"
    }
}

enum class User