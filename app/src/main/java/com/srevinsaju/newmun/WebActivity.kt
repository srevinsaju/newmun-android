package com.srevinsaju.newmun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        WebView.settings.allowContentAccess = true
        WebView.settings.allowUniversalAccessFromFileURLs = true
        WebView.settings.javaScriptEnabled = true
        WebView.settings.setSupportMultipleWindows(true)
        WebView.settings.setAppCachePath("/data/data/com.srevinsaju.newmun/cache/")
        WebView.settings.setAppCacheEnabled(true)
        WebView.loadUrl("https://newindianschoolmun-live.weebly.com")

    }
}
