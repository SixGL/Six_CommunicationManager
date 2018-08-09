package tsou.cn.lib_webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;


/**
 * Created by admin on 2018/6/6.
 */

public class Webset extends BaseWebset {
    /**
     * 使用方式：
     * 1.简洁用法：Webset.newInstance().with(mCtonext, mWebView).url("网页链接").setWebsetting();
     * 2.
     */

    private Context mCtonext;
    private String webUrl;
    private String jsName;// js交互，定义交互规范
    private WebView mWebView;
    private boolean jsInteraction = false;// js 交互
    private boolean cahceEnabled = false;// 缓存开关
    private JsInteraction mJsInteraction;
    static final String AGENTWEB_CACHE_PATCH = File.separator + "xiaoguo-cache";


    /**
     * @param
     */
    public static Webset newInstance() {
        Webset webset = new Webset();
        return webset;
    }

    /**
     * @param context 初始化上下文
     */
    public <T extends Webset> T with(Context context, WebView webView) {
        this.mCtonext = context;
        this.mWebView = webView;
        return (T) this;
    }

    /**
     * @param url webview加载的url
     */
    public <T extends Webset> T url(String url) {
        this.webUrl = url;
        return (T) this;
    }

    /**
     * @param b true 设置js缓存  默认是不支持的
     */
    public <T extends Webset> T setCacheEnable(boolean b) {
        this.cahceEnabled = b;
        return (T) this;
    }

    /**
     * @param b      true 设置js交互  默认是不支持的
     * @param jsName js交互，定义的规范标识
     */
    public <T extends Webset> T setJavaScriptEnabled(boolean b, String jsName, JsInteraction jsInteraction) {
        if (b == true) {
            this.jsName = jsName;
            this.jsInteraction = b;
            this.mJsInteraction = jsInteraction;
        }
        return (T) this;
    }

    /**
     * 设置websetting
     */
    public <T extends Webset> T setWebsetting() {
        addWetSetting();
        return (T) this;
    }

    /**
     * 需要在WebViewClient onPageStarted和 onPageFinished方法中进行操作就调用这个
     */
    public <T extends Webset> T setWebViewClient(WebViewClientListener webViewClient) {
        webViewClientListener = webViewClient;
        setWebViewClient(mWebView);
        return (T) this;
    }

    public <T extends Webset> T setWebChromeClient(WebChromeClientListener webChromeClient) {
        webChromeClientListener = webChromeClient;
        setWebChromeClient(mWebView);
        return (T) this;
    }

    @SuppressLint({"JavascriptInterface", "NewApi"})
    private void addWetSetting() {
        WebSettings mWebSettings = mWebView.getSettings();
        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
        mWebView.setVerticalScrollBarEnabled(false); //垂直不显示
        mWebSettings.setBuiltInZoomControls(false);
        mWebSettings.setSavePassword(false);
        mWebSettings.setJavaScriptEnabled(jsInteraction);
        if (jsInteraction == true) {
            mWebView.addJavascriptInterface(mJsInteraction, jsName);
        }
        if (cahceEnabled == true) {
            if (NetStatusUtil.isNetworkConnected(mCtonext)) {
                //根据cache-control获取数据。
                mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                // 没网，则从本地获取，即离线加载
                mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 适配5.0不允许http和https混合使用情况
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebSettings.setTextZoom(100);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setLoadsImagesAutomatically(true);////支持自动加载图片
        mWebSettings.setSupportMultipleWindows(false);
        // 是否阻塞加载网络图片  协议http or https
        mWebSettings.setBlockNetworkImage(false);
        // 允许加载本地文件html  file协议
        mWebSettings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // 通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
            mWebSettings.setAllowFileAccessFromFileURLs(false);
            // 允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
            mWebSettings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else {
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        mWebSettings.setLoadWithOverviewMode(false);// 	是否使用概览模式
        mWebSettings.setUseWideViewPort(false);// 	是否允许使用 <viewport> 标签 将图片调整到适合webview的大小
        mWebSettings.setDomStorageEnabled(true);// 	是否使用文档存储
        mWebSettings.setNeedInitialFocus(true);// //当webview调用requestFocus时为webview设置节点
        mWebSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        mWebSettings.setGeolocationEnabled(true);// 是否使用地理位置
        // 缓存路径配置
        String dir = mCtonext.getCacheDir().getAbsolutePath() + AGENTWEB_CACHE_PATCH;
        // 设置数据库路径  api19 已经废弃,这里只针对 webkit 起作用
        mWebSettings.setGeolocationDatabasePath(dir);
        mWebSettings.setDatabasePath(dir);
        mWebSettings.setAppCachePath(dir);
        // 缓存文件最大值
        mWebSettings.setAppCacheMaxSize(Long.MAX_VALUE);
//        mWebView.setWebChromeClient(wvcc);
        mWebView.loadUrl(webUrl);
    }
}
