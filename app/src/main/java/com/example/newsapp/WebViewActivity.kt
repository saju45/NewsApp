package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.newsapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url=intent.getStringExtra("url") as String
        binding.webView.webViewClient= WebViewClient()
        binding.webView.loadUrl(url)
    }
}