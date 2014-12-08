package com.clipandbooks.funapp.sample.linkshare;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    EditText mInputUrlData;
    Button mFacebookBtn;
    Button mTwitterBtn;
    Button mKakaotalkBtn;
    Button mInfoBtn;
    WebView mWebView;
    LinearLayout mLoadingView;
    Toast mToastPopup;
        
    private final String localPage = "file:///android_asset/html_pages/FirstPage.htm";
    private final String blankPage = "about:blank";
    private final String facebookLinkShare = "http://www.facebook.com/sharer.php?";
    private final String twitterLinkShare = "https://twitter.com/intent/tweet?";
    private final String defaultUrlData = "http://raspberrybiscuit.wordpress.com";
    private final String kakaoDevSite = "http://developers.kakao.com";
    private final String waterMarkcomment = "Hello, Link!!";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface fontFamily = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        
        mFacebookBtn = (Button)findViewById(R.id.facebook_btn);
        mFacebookBtn.setTypeface(fontFamily);
        mTwitterBtn = (Button)findViewById(R.id.twitter_btn);
        mTwitterBtn.setTypeface(fontFamily);
        mKakaotalkBtn = (Button)findViewById(R.id.kakaotalk_btn);
        mKakaotalkBtn.setTypeface(fontFamily);
        mInfoBtn = (Button)findViewById(R.id.info_btn);
        mInfoBtn.setTypeface(fontFamily);
        
        mWebView = (WebView)findViewById(R.id.web);
        mLoadingView = (LinearLayout)findViewById(R.id.wait);
        
        mFacebookBtn.setOnClickListener(this);
        mTwitterBtn.setOnClickListener(this);
        mKakaotalkBtn.setOnClickListener(this);
        mInfoBtn.setOnClickListener(this);
        
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebClient());
        mWebView.loadUrl(localPage);
    }

    @Override
    public void onClick(View v) {
        StringBuilder uri = new StringBuilder();
        
        mWebView.loadUrl(blankPage);
        switch (v.getId()) {
        case R.id.facebook_btn :
            uri.append(facebookLinkShare).append("u=").append(defaultUrlData);
            mWebView.loadUrl(uri.toString());
            mToastPopup = Toast.makeText(getApplicationContext(), getString(R.string.noti_facebook_share), Toast.LENGTH_SHORT);
            mToastPopup.show();
            break;
        case R.id.twitter_btn :
            uri.append(twitterLinkShare).append("text=").append(waterMarkcomment)
            .append("&url=").append(defaultUrlData);
            mWebView.loadUrl(uri.toString());
            mToastPopup = Toast.makeText(getApplicationContext(), getString(R.string.noti_twitter_share), Toast.LENGTH_SHORT);
            mToastPopup.show();
            mWebView.loadUrl(uri.toString());
            break;
        case R.id.kakaotalk_btn :
            mToastPopup = Toast.makeText(getApplicationContext(), getString(R.string.noti_kakaotalk_share), Toast.LENGTH_SHORT);
            mToastPopup.show();
            mWebView.loadUrl(kakaoDevSite);
            break;
        case R.id.info_btn :
            Intent intent = new Intent(MainActivity.this, InfoPage.class);
            startActivity(intent);
            break;
        
        }
        
    }
    
    private class MyWebClient extends WebViewClient {
        @Override    
        public boolean shouldOverrideUrlLoading(WebView view, String url) {        
            view.loadUrl(url);        
         return true;    
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            if (!url.matches("file://")) {
                mLoadingView.setVisibility(View.VISIBLE);
                Log.d("TAG", "onPageStarted("+ url + ")");
            } else {
                Log.d("TAG", "onPageStarted: internal pages");
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //super.onPageFinished(view, url);
            mLoadingView.setVisibility(View.INVISIBLE);
            Log.d("TAG", "onPageFinished("+ url + ")");
        }
    }
    
}
