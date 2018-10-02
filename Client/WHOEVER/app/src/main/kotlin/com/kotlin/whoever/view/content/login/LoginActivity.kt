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
import com.kotlin.whoever.model.jsondata.User
import com.kotlin.whoever.view.content.main.MainActivity
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.kotlin.whoever.constants.constants.Companion.RC_SIGN_IN
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast




class LoginActivity : AppCompatActivity() {

    private val callback by lazy { SessionCallback() }
    private var kakaoAccessToken:String? = null
    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(R.string.server_client_id.toString()).requestEmail().build()
    }
    private val mGoogleSignInClient by lazy { GoogleSignIn.getClient(this, gso) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
            Log.d("hoho", task.toString())
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
            Log.d("hoho", e.statusMessage)
            toast("error").show()
        }

    }
    inner class SessionCallback: ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?){}
        override fun onSessionOpened() {
            kakaoAccessToken = Session.getCurrentSession().accessToken

            Log.d("hoho", kakaoAccessToken.toString())


            // 비동기식 데이터 주고 받는 서버 부분(나중에 viewModel로 뺀다)
            val call= provideLogin().getKakaoAccesToken(kakaoAccessToken.toString())
            call.enqueue(object : Callback<User>{
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("hoho", "fail")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user = response.body()
                    if(user != null) {
                        Log.d("hoho", user!!.wUser_name)
                        updateUI()
                    }else{
                        Log.d("hoho", "씨발!!!")
                    }
                }
            })
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



