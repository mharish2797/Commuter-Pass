package pass.commuter.os.commuterpass;

/**
 * Created on: 30-03-2016.
 *	Author: Harish Mohan
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class Webber extends Activity{
    private WebView wv1;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webber);
        int status=NetworkStatus.getNetworkStatus(this);
        if(status==0)
        {
            Toast.makeText(this,"Please connect to Internet for better Interface...",Toast.LENGTH_LONG).show();
        }
        wv1=(WebView)findViewById(R.id.webview);
        wv1.setWebViewClient(new MyBrowser());
        String url ="https://scfu.onlinesbi.com/vfim/login.htm";
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(url);
    }

    public static class NetworkStatus {
        public static int getNetworkStatus(Context context){
            ConnectivityManager connectivityManager=(ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null){
                switch (networkInfo.getType()){
                    case ConnectivityManager.TYPE_MOBILE:
                        return 1;
                    case ConnectivityManager.TYPE_WIFI:
                        return 2;
                    default:
                        return  0;
                }
            }else {
                return 0;
            }
        }}

    private class MyBrowser extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
