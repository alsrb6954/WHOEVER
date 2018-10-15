package com.kotlin.whoever.view.vm.login

import android.arch.lifecycle.ViewModel
import com.kotlin.whoever.common.provideLogin
import com.kotlin.whoever.constants.constants.Companion.loginErrorMeassage
import com.kotlin.whoever.model.jsondata.User
import com.kotlin.whoever.unit.PManager
import com.kotlin.whoever.unit.SupportOptional
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class LoginViewModel:ViewModel() {
    // user 정보(true: 로그인 성공, false: 로그인 실패)
    val isLogin: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)
    // error 메시지
    val errorMessage: PublishSubject<String> = PublishSubject.create()
    // 작업 진행상태 표시
    val isLoading: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    fun requestKakaoAccessToken(accessToken:String) = provideLogin().getKakaoAccesToken(accessToken)
            .doOnSubscribe { isLoading.onNext(true) }
            .doOnTerminate { isLoading.onNext(false) }
            .subscribe({it ->
                userSave(it,accessToken)
                isLogin.onNext(true)
            }){
                isLogin.onNext(false)
                errorMessage.onNext(it.message ?: loginErrorMeassage)
            }!!
    //구글
    fun requestGoogleAccessToken(accessToken: String) = provideLogin().getGoogleAccesToken(accessToken)
            .doOnSubscribe { isLoading.onNext(true) }
            .doOnTerminate { isLoading.onNext(false) }
            .subscribe ({
                userSave(it,accessToken)
                isLogin.onNext(true)
            }){
                isLogin.onNext(false)
                errorMessage.onNext(it.message ?: loginErrorMeassage)
            }
    private fun userSave(it:User, accessToken: String){
        PManager.init()
        PManager.setUserEmail(it.wUser_email)
        PManager.setUserName(it.wUser_name)
        PManager.setUserProfileImageUrl(it.wUser_profileImageUrl)
        PManager.setUserId(it.wUser_id)
        PManager.setUserKakaoId(accessToken)
        PManager.setUserIsLogin("1")
    }
}