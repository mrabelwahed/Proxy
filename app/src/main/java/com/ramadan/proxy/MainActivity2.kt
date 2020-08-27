package com.ramadan.proxy

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main2.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class MainActivity2 : AppCompatActivity() {
    lateinit var RAW_REDIRECT_URI: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val APP_ID = "2693353104266783"
        RAW_REDIRECT_URI = "fb$APP_ID://authorize//"

        // UTF8 adress so it can go along the http request
        var REDIRECT_URI = "";
        try {
            REDIRECT_URI = URLEncoder.encode(RAW_REDIRECT_URI, "UTF-8");
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace();
        }

        // get the code or token
        val TOKEN_REQUEST =
            "https://www.facebook.com/v8.0/dialog/oauth?client_id=" + APP_ID + "&redirect_uri=" +
                    REDIRECT_URI + "&scope=email" + "&response_type=token%20granted_scopes" + "&state=\'{st=state123abc,ds=123456789}\'";


        webview.webViewClient = object : WebViewClient() {
            @Override
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url);
                return true;
            }


            override fun onPageFinished(view: WebView?, url: String?) {
                if (url!!.startsWith("fb")) {
                    val accessTokenPair = url.substring(url.indexOf("#"),url.indexOf("&"))
                    val accessToken = accessTokenPair.substring(accessTokenPair.indexOf("=")+1)


                    Log.e("TTTTT",accessToken)
                    webview.visibility = View.GONE
                    Observable.fromCallable {
                        val data = getProfileData(accessToken)
                      data
                    }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                           it?.let {
                               name.visibility = View.VISIBLE
                               image.visibility = View.VISIBLE

                               Log.e("DDD",it)
                               val jsonObj = JSONObject(it)
                               val nameStr = jsonObj.optString("name")
                               val userId = jsonObj.optString("id")
                               name.text = nameStr
                               Picasso.get().load("https://graph.facebook.com/$userId/picture?return_ssl_resources=1").into(image)
                           }
                        }
                }

            }

        }

        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(TOKEN_REQUEST);

    }

    fun getProfileData(accessToken: String)  : String?{
        val url = "https://graph.facebook.com/me?access_token=$accessToken"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build();

        val response = client.newCall(request).execute()
        return response.body()?.string()
    }

}




