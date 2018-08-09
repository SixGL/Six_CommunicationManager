package tsou.cn.lib_webview;

import android.graphics.Bitmap;
import android.webkit.WebView;

/**
 * Created by admin on 2018/6/6.
 */

public interface WebViewClientListener {

    public void onPageStarted(WebView view, String url, Bitmap favicon);

    public void onPageFinished(WebView view, String url);
}
