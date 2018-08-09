package tsou.cn.lib_webview;

import android.content.Context;
import android.webkit.JavascriptInterface;

import java.io.Serializable;

/**
 * Created by admin on 2018/6/6.
 */

public class JsInteraction implements Serializable {

    public Context mContext;

    public JsInteraction(Context mContext) {
        this.mContext = mContext;
    }

//    public void initCtx(Context context) {
//        this.mContext=context;
//    }


    @JavascriptInterface
    public void getDeviceId() {
    }

}
