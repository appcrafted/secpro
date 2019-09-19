package com.securitypro.proapp.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import com.securitypro.proapp.Model.Licence;
import com.securitypro.proapp.R;
import com.securitypro.proapp.Utility.PostHttp;
import com.securitypro.proapp.Utility.Responsable;
import com.securitypro.proapp.Utility.SavePref;
import com.securitypro.proapp.Utility.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Splash extends Activity {

    SavePref savePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        savePref = new SavePref(this);

        if (Utility.haveNetworkConnection(this)){
            checkLicenceById();
        }

        if (Build.VERSION.SDK_INT >= 23) {
            //requestPermissions();
            startTrueActivity();
        } else {
            startTrueActivity();
        }
    }

    public void startTrueActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (savePref.checkLicense()) {
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                } else {
                    // TODO: 12-Aug-18
                    //startActivity(new Intent(Splash.this, RegisterKey.class));
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                }
            }
        }, 2000);
    }

    private void checkLicenceById() {

        @SuppressLint("HardwareIds")
        String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Map<String, String> map = new HashMap<>();
        map.put("deviceid", androidId);
        new PostHttp("https://rudraumantispy.herokuapp.com/getdetailById", map, new Responsable() {

            @Override
            public void done(String reply) {
                if (reply == null){
                    savePref.setLicense(new Licence("", "", "", "", ""));
                }else {
                    if (Utility.haveNetworkConnection(Splash.this)) {
                        try {
                            JSONObject obj = new JSONObject(reply);
                            JSONObject obj1 = obj.getJSONObject("doc");

                            String _id = obj1.getString("_id");
                            String key = obj1.getString("key");
                            String deviceid = obj1.getString("deviceid");
                            String ad = obj1.getString("ad");
                            String ed = obj1.getString("ed");

                            savePref.setLicense(new Licence(_id, key, ad, ed, deviceid));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Splash.this, "" + e, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Splash.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).execute();

    }
}


// TODO: 31-Mar-18 call getAppsByPerm
// TODO: 31-Mar-18 on back pressed
// TODO: 31-Mar-18 ignore setting
