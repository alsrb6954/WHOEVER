package com.kotlin.whoever.constants

class constants {
    companion object{
        const val RC_SIGN_IN = 1000
        const val URL = "http://211.211.204.120:3000"
        const val tokenField = "wAccess_token"
        const val requestKakaoLogin = "auth/kakao"
        const val requestGoogleLogin = "auth/google"
        const val loginErrorMeassage = "Unexpected error"

        const val KEY_USERNAME = "userName"
        const val KEY_PROFILEIMAGEURL = "userProfileImageUrl"
        const val KEY_USEREMAIL = "userEmail"
        const val KEY_USERID = "userId"
        const val KEY_ISLOGIN = "isLogin"

        // SNS 토큰
        const val KEY_KAKAO_ID = "kakaoId"
        const val KEY_FCM_REG_ID = "fcmToken"

        const val ALARM_BADGE_NUMBER = "alarmBadgeNumber"
        //FCM알람관련 뱃지카운터.//
        var badgeNumber = 0
    }
}

enum class User