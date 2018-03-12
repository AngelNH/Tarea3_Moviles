package com.iteso.classproyect.tools;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by inqui on 12/03/2018.
 */

public class WebAppInterface {
    Context context;
    public WebAppInterface(Context context){
        this.context = context;
    }
    @JavascriptInterface
    public void showToast(String toast){
        Toast.makeText(context,toast, Toast.LENGTH_SHORT).show();
    }
}
