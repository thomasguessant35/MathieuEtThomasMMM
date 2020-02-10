package com.example.mathieuetthomas;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class AddActivity extends Activity {

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView v, String url) {
            v.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText name = findViewById(R.id.editText);
        final WebView webview = findViewById(R.id.webview);

        //webview.loadUrl("http://www.google.fr/");

        webview.setWebViewClient(new MyWebViewClient() );

        name.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                webview.loadUrl("http://www.google.fr/search?q=" + name.getText().toString() + "&tbm=isch");

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });
    }
}
