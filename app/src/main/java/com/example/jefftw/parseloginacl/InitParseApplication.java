package com.example.jefftw.parseloginacl;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by jefftw on 8/21/14.
 */

public class InitParseApplication extends Application {

    static final String TAG = "MyApp";
    public boolean enableLocalStore = true;

    @Override
    public void onCreate() {
        super.onCreate();

        if (enableLocalStore) {
            Parse.enableLocalDatastore(getApplicationContext());
        }
        Parse.initialize(this, "PARSE_APPLICATION_ID", "PARSE_CLIENT_KEY");

    }

}