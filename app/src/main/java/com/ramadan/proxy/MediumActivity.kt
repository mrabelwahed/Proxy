package com.ramadan.proxy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_medium.*

class MediumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium)
        webView.loadUrl("http://185.203.116.28:9080/medium/swlh/build-a-rest-api-in-golang-with-swagger-and-hot-reload-of-everything-6247a8ae8618")
    }
}