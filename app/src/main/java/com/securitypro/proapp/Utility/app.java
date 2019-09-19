package com.securitypro.proapp.Utility;

import android.app.Application;
import android.content.Intent;

import com.securitypro.proapp.Service.appService;


/**
 * Created by Chetna on 03-Mar-18.
 */

public class app extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        final Intent intent = new Intent(this, appService.class);
        intent.setAction("camnotification");
        //startService(intent);

    }
}
