package org.intracode.heliot;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by August on 2018-04-25.
 */

public class Heliot extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
