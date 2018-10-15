package com.kotlin.whoever.unit

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.kotlin.whoever.common.GlobalApplication
import com.kotlin.whoever.constants.constants.Companion.ALARM_BADGE_NUMBER
import com.kotlin.whoever.constants.constants.Companion.KEY_ISLOGIN
import com.kotlin.whoever.constants.constants.Companion.KEY_KAKAO_ID
import com.kotlin.whoever.constants.constants.Companion.KEY_PROFILEIMAGEURL
import com.kotlin.whoever.constants.constants.Companion.KEY_USEREMAIL
import com.kotlin.whoever.constants.constants.Companion.KEY_USERID
import com.kotlin.whoever.constants.constants.Companion.KEY_USERNAME
import com.kotlin.whoever.constants.constants.Companion.badgeNumber

object PManager{
    private var pManager:PropertyManager? = null

    fun init(){
        if(pManager == null)
            pManager = PropertyManager()
    }
    // 유저 이름
    fun getUserName():String = pManager?.mProfile!!.getString(KEY_USERNAME,"") // 만약에 값이 없으면 ""으로 나온다
    fun setUserName(userName: String){
        pManager?.mEditor!!.putString(KEY_USERNAME, userName)
        pManager?.mEditor!!.commit() // 저장 후 완료한다
    }
    // 유저 아이디
    fun getUserId():String = pManager?.mProfile!!.getString(KEY_USERID,"")
    fun setUserId(userId: String){
        pManager?.mEditor!!.putString(KEY_USERID, userId)
        pManager?.mEditor!!.commit()
    }
    // 유저 프로필 사진
    fun getUserProfileImageUrl():String = pManager?.mProfile!!.getString(KEY_PROFILEIMAGEURL,"")
    fun setUserProfileImageUrl(userProfileImageUrl: String){
        pManager?.mEditor!!.putString(KEY_PROFILEIMAGEURL, userProfileImageUrl)
        pManager?.mEditor!!.commit()
    }
    // 유저 이메일
    fun getUserEmail():String = pManager?.mProfile!!.getString(KEY_USEREMAIL,"")
    fun setUserEmail(userEmail: String){
        pManager?.mEditor!!.putString(KEY_USEREMAIL, userEmail)
        pManager?.mEditor!!.commit()
    }
    // 로그인 여부 0-> 로그아웃 상태 1-> 로그인 상태
    fun getUserIsLogin():String = pManager?.mProfile!!.getString(KEY_ISLOGIN,"0")
    fun setUserIsLogin(userIsLogin: String){
        pManager?.mEditor!!.putString(KEY_ISLOGIN, userIsLogin)
        pManager?.mEditor!!.commit()
    }
    // 카카오톡 토큰
    fun getUserKakaoId():String = pManager?.mProfile!!.getString(KEY_KAKAO_ID,"")
    fun setUserKakaoId(userKakaoId: String){
        pManager?.mEditor!!.putString(KEY_KAKAO_ID, userKakaoId)
        pManager?.mEditor!!.commit()
    }
//    // FCM 토큰
//    fun getUserFcmRegId():String = pManager?.mProfile!!.getString(PropertyManager.KEY_FCM_REG_ID,"")
//    fun setUserFcmRegId(userFcmRegId: String){
//        pManager?.mEditor!!.putString(PropertyManager.KEY_FCM_REG_ID, userFcmRegId)
//        pManager?.mEditor!!.commit()
//    }
    // 알람 뱃지 숫자
    fun getBadgeNumber():Int = pManager?.mProfile!!.getInt(ALARM_BADGE_NUMBER,badgeNumber)
    fun setBadgeNumber(badgeNumber: Int){
        pManager?.mEditor!!.putInt(ALARM_BADGE_NUMBER, badgeNumber)
        pManager?.mEditor!!.commit()
    }
}



class PropertyManager {
    private val context: Context = GlobalApplication.context
    val mProfile: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }
    val mEditor: SharedPreferences.Editor by lazy {
        mProfile.edit()
    }
}
