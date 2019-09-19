package com.securitypro.proapp.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.securitypro.proapp.Service.appService;
import com.securitypro.proapp.Utility.SavePref;

/**
 * Created by Chetna on 28-Mar-18.
 */

public class WifiReceiver extends BroadcastReceiver {

    String TAG = getClass().getSimpleName();
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {

        mContext = context;
        SavePref savePref = new SavePref(context);


        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI &&
                    networkInfo.isConnected()) {
                // Wifi is connected
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ssid = wifiInfo.getSSID();

                Log.e(TAG, " -- Wifi connected --- " + " SSID " + ssid );
                Toast.makeText(context, "wifi connected", Toast.LENGTH_SHORT).show();

                if (savePref.getSwitches().mainswitch && savePref.getSwitches().wifi_switch) {
                    //Toast.makeText(context, "wifi on", Toast.LENGTH_SHORT).show();

                    WifiManager wifiM = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    if (wifiM != null) {
                        wifiM.setWifiEnabled(false);
                    }
                }else if (savePref.getSwitches().mainswitch){
                    Intent not = new Intent(context, appService.class);
                    not.setAction("WiFis");
                    context.startService(not);
                }

            }
        }
        else if (intent.getAction().equalsIgnoreCase(WifiManager.WIFI_STATE_CHANGED_ACTION))
        {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
            if (wifiState == WifiManager.WIFI_STATE_DISABLED)
            {
                Log.e(TAG, " ----- Wifi  Disconnected ----- ");
                //Toast.makeText(context, "wifi off", Toast.LENGTH_SHORT).show();
            }else {
                /*if (savePref.getSwitches().mainswitch && savePref.getSwitches().wifi_switch) {
                    //Toast.makeText(context, "wifi on", Toast.LENGTH_SHORT).show();

                    WifiManager wifiM = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    if (wifiM != null) {
                        wifiM.setWifiEnabled(false);
                    }
                }else if (savePref.getSwitches().mainswitch){
                    Intent not = new Intent(context, appService.class);
                    not.setAction("WiFis");
                    context.startService(not);
                }*/
            }

        }
    }
}