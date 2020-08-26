package com.ramadan.proxy

import android.app.Application
import com.ramadan.proxy.lib.EasyProxy
import com.ramadan.proxy.lib.ProxyInfo
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Authenticator
import java.net.PasswordAuthentication
import java.net.URL
import java.net.URLConnection
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future


class App : Application() {
    override fun onCreate() {
        super.onCreate()
       //EasyProxy.init(ProxyInfo("187.60.46.229", 8080),true)

    }
}