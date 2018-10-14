package com.kotlin.whoever.view.vm.login

import android.arch.lifecycle.ViewModel
import com.kotlin.whoever.common.provideLogin
import com.kotlin.whoever.constants.constants.Companion.loginErrorMeassage
import com.kotlin.whoever.unit.SupportOptional
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class LoginViewModel:ViewModel() {

    // user 정보
    val kakaoAccessToken: BehaviorSubject<SupportOptional<String>> = BehaviorSubject.create()
    // error 메시지
    val errorMessage: PublishSubject<String> = PublishSubject.create()
    // 작업 진행상태 표시
    val isLoading: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    fun requestKakaoAccessToken(accessToken:String) = provideLogin().getKakaoAccesToken(accessToken)
            .doOnSubscribe { isLoading.onNext(true) }
            .doOnTerminate { isLoading.onNext(false) }
            .subscribe({it ->


            }){
                errorMessage.onNext(it.message ?: loginErrorMeassage)
            }!!
    fun requestGoogleAccessToken(accessToken: String) = provideLogin().getGoogleAccesToken(accessToken)
            .doOnSubscribe { isLoading.onNext(true) }
            .doOnTerminate { isLoading.onNext(false) }
            .subscribe ({

            }){
                errorMessage.onNext(it.message ?: loginErrorMeassage)
            }
}