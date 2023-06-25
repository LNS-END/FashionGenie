package com.example.fashion;

import android.widget.Toast;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class chatdirect extends AppCompatActivity {
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        // 자바스크립트로 인터페이스 제공
        webView.addJavascriptInterface(new JavaScriptInterface(), "Android");

        // 웹뷰 클라이언트 설정
        webView.setWebViewClient(new WebViewClient());

        // 웹뷰 크롬 클라이언트 설정
        webView.setWebChromeClient(new WebChromeClient());

        // HTML 파일 로드
        webView.loadUrl("file:///android_asset/index.html");
    }

    // 자바스크립트 인터페이스 클래스
    public class JavaScriptInterface {
        @android.webkit.JavascriptInterface
        public void showToast(String message) {
            // 안드로이드 토스트 메시지 표시
            Toast.makeText(chatdirect.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}