package red.txn.webNavi;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cafex.liveassist.LiveAssistAuth;
import com.cafex.liveassist.LiveAssistChatStyle;
import com.cafex.liveassist.LiveAssistConfig;
import com.cafex.liveassist.LiveAssistDelegate;
import com.cafex.liveassist.LiveAssistView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LiveAssistDelegate {

    final String TAG = "MainActivityFragment";

    private String url_ = "https://";
//    private String url_ = "https://txn.myds.me/api/login.html";
    private String authMethod_ = "";
    private Integer accountId_ = -1;

    private String jwt_;

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        url_ += prefs.getString( getString(R.string.pref_key_webUrl),
                                getString(R.string.pref_default_webUrl) );
        authMethod_ = prefs.getString( getString(R.string.pref_key_authMethod),
                                       getString(R.string.pref_default_authMethod) );
        String tmp = prefs.getString( getString(R.string.pref_key_la365Account),
                                      getString(R.string.pref_default_la365Account) );

        accountId_ = Integer.parseInt(tmp);

        this.setupWebView(v);

        this.setupLiveAssistView(v);

        return v;
    }

    private void setupLiveAssistView(View v) {
//        int accountId = 23320646;
        String[] sections = {"android"};
        LiveAssistChatStyle chatStyle = LiveAssistChatStyle.AUTO;
        LiveAssistConfig config = new LiveAssistConfig(accountId_, sections, chatStyle);

//        config.setJavascriptMethodName(getResources().getString(R.string.la_authMethod));
//        config.setJavascriptMethodName("auth.getAuthenticationToken");
        config.setJavascriptMethodName(authMethod_);
        config.setLiveAssistDelegate(this);

        LiveAssistView laView = v.findViewById(R.id.liveAssistView);
        laView.loadWithConfig(config);
    }

    private void setupWebView(View v) {
        WebView wv = v.findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient());

        // javascript enable need for la365a
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new JSInterface(this.getContext()), "android");

        wv.loadUrl(url_);
    }

    @Override
    public void authoriseChatWithCallback(LiveAssistAuth liveAssistAuth) {

        liveAssistAuth.authorise(this.jwt_);
    }

    public class JSInterface {
        Context context_;

        public  JSInterface(Context c) {
            context_ = c;
        }

        @JavascriptInterface
        public void getJwt(String jwt) {
            jwt_ = jwt;
            Log.i("JWT", jwt);
        }
    }

}
