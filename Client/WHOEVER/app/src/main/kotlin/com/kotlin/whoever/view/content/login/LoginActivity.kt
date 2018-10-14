package com.kotlin.whoever.view.content.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.kotlin.whoever.common.AutoClearedDisposable
import com.kotlin.whoever.extensions.plusAssign
import com.kotlin.whoever.constants.constants.Companion.RC_SIGN_IN
import com.kotlin.whoever.view.vm.login.LoginViewModel
import com.kotlin.whoever.view.vm.login.LoginViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast




class LoginActivity : AppCompatActivity() {

    private val callback by lazy { SessionCallback() }
    private var kakaoAccessToken:String? = null
    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(R.string.server_client_id.toString()).requestEmail().build()
    }
    private val mGoogleSignInClient by lazy { GoogleSignIn.getClient(this, gso) }
    private val disposables = AutoClearedDisposable(this)
    private val viewDisposables = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)
    private val viewModelFactory by lazy { LoginViewModelFactory() }
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]

        // LifeCycle.Observer() 함수를 사용하여 AutoClearedDisposable 객체를 옵서버에 등록
        lifecycle += disposables
        lifecycle += viewDisposables

        // 에러메시지 구독
        viewDisposables += viewModel.errorMessage
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{message -> showError(message)}

        // 작업 진행 여부 구독
        viewDisposables += viewModel.isLoading
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isLoading -> if(isLoading) showLoading() else hideLoading() }

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

            viewModel.requestKakaoAccessToken(kakaoAccessToken.toString())
        }
    }
    private fun updateUI(){ startActivity<MainActivity>() }
    private fun showLoading(){
        login_loading.visibility = View.VISIBLE
        login_loading.loop(true)
        login_loading.playAnimation()
    }
    private fun hideLoading(){
        login_loading.visibility = View.INVISIBLE
        login_loading.loop(false)
        login_loading.pauseAnimation()
    }
    private fun showError(message: String){ longToast(message).show()}
    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account != null){
            updateUI()
        }
    }
}



