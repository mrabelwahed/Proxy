package com.ramadan.proxy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ramadan.proxy.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_firebase_login.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

const val API_KEY = "AIzaSyAOmp5bwp5GT5wu6a_eXZCzd4z28okgqXg"
const val BASE_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?"
class FirebaseLogin : AppCompatActivity() {
    val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_login)

        login.setOnClickListener {
            val emailAddress = email.text.toString()
            val pass = password.text.toString()

          val disposable =  Single.fromCallable {
               login(emailAddress, pass)
           }
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe {
                   data->
                   Toast.makeText(this," Auth Token"+data,Toast.LENGTH_SHORT).show()
               }
            compositeDisposable.add(disposable)
        }

    }

    private fun login(email:String , password:String) : String?{
            val okHttpClient = OkHttpClient()
            val requestBody = FormBody.Builder()
                .add("email" ,email)
                .add("password",password)
                .add("returnSecureToken","true")
                .build()

            val request = Request.Builder()
                .url(BASE_URL+"key="+ API_KEY)
                .post(requestBody)
                .build()

           val response =  okHttpClient.newCall(request).execute()
            return response.body()?.string()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}