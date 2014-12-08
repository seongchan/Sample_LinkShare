package com.clipandbooks.funapp.sample.linkshare;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoPage extends Activity {

    private TextView refContents;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infopage);
    }

}
