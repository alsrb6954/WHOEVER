package com.kotlin.whoever.view.content.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import com.kotlin.whoever.R
import com.kotlin.whoever.common.provideLogin
import com.kotlin.whoever.view.content.main.MainActivity
import org.jetbrains.anko.startActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.kotlin.whoever.common.AutoClearedDisposable
import com.kotlin.whoever.extensions.plusAssign
import com.kotlin.whoever.constants.constants.Companion.RC_SIGN_IN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast




class LoginActivity : AppCompatActivity() {

    private val callback by lazy { SessionCallback() }
    private var kakaoAccessToken:String? = null
    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(R.string.server_client_id.toString()).requestEmail().build()
    }
    private val mGoogleSignInClient by lazy { GoogleSignIn.getClient(this, gso) }
    internal val disposables = AutoClearedDisposable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // LifeCycle.Observer() 함수를 사용하여 AutoClearedDisposable 객체를 옵서버에 등록
        lifecycle += disposables

        btn_kakao_login.setOnClickListener {
            kakaoLogin()
        }
        btn_google_login.setOnClickListener {
            googleLogin()
        }
    }

    // kakao
    private fun kakaoLogin() {
        Session.getCurrentSession().apply {
            addCallback(callback)
            open(AuthType.KAKAO_LOGIN_ALL, this@LoginActivity)
        }
    }
    // google
    private fun googleLogin(){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            //Log.d("hoho", task.result.idToken)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        Log.d("hoho", "ggg $completedTask")
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val idToken = account.idToken
            Log.d("hoho", account.toString())
            Log.d("hoho", idToken)
            // Signed in successfully, show authenticated UI.
            //updateUI()
        } catch (e: ApiException) {
            Log.d("hoho", e.localizedMessage)
            toast("error").show()
        }

    }

    inner class SessionCallback: ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?){}
        override fun onSessionOpened() {
            kakaoAccessToken = Session.getCurrentSession().accessToken

            Log.d("hoho", kakaoAccessToken.toString())

            disposables += provideLogin().getKakaoAccesToken(kakaoAccessToken.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe{} // 수행을 시작할때
                    .doOnTerminate{} // 수행을 끝났을때
                    .subscribe({user ->
                        Log.d("hoho", user.wUser_name)
                        updateUI()
                    }) // 옵서버블을 구독
                    {
                        //에러
                        Log.d("hoho", "씨발!!!")
                    }

        }
    }
    private fun updateUI(){
        startActivity<MainActivity>()
    }
    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account != null){
            updateUI()
        }
    }
}



