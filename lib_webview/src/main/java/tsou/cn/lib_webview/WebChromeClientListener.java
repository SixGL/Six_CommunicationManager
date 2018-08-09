package tsou.cn.lib_webview;

import android.webkit.WebView;

/**
 * Created by admin on 2018/6/6.
 */

public interface WebChromeClientListener {
    public void onReceivedTitle(WebView view, String title);
}
