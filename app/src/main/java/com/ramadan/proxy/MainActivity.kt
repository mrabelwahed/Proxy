package com.ramadan.proxy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    lateinit var callbackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                // App code
                loginResult?.let {
                    userID.text = it.accessToken.userId
                    val url =
                        "https://graph.facebook.com/" + it.accessToken.userId + "/picture?return_ssl_resources=1"
                    Picasso.get().load(url).into(userImage)
                }

            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })

        btn.setOnClickListener {
         startActivity(Intent(this,MainActivity2::class.java))
        }

        mediumBtn.setOnClickListener {
            startActivity(Intent(this,MediumActivity::class.java))
        }

      firebaseBtn.setOnClickListener {
          startActivity(Intent(this,FirebaseLogin::class.java))
      }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }


}