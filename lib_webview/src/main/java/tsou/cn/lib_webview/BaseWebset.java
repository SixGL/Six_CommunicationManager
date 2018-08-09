package tsou.cn.lib_webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by admin on 2018/6/6.
 */

public class BaseWebset {


    public  WebViewClientListener webViewClientListener;
    public WebChromeClientListener webChromeClientListener;


    public void setWebViewClient(WebView mWebView) {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (webViewClientListener != null) {
                    webViewClientListener.onPageStarted(view, url, favicon);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (webViewClientListener != null) {
                    webViewClientListener.onPageFinished(view, url);
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//接受所有https证书
            }
        });
    }

    public void setWebChromeClient(WebView mWebView) {
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (webChromeClientListener != null) {
                    webChromeClientListener.onReceivedTitle(view, title);
                }
            }
        });
    }


}
