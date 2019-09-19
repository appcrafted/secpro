package com.securitypro.proapp.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.securitypro.proapp.Service.appService;
import com.securitypro.proapp.Utility.SavePref;

/**
 * Created by Chetna on 02-Apr-18.
 */

public class CameraEventReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        SavePref savePref = new SavePref(context);
        if (savePref.getSwitches().mainswitch && savePref.getSwitches().cam_switch) {
            // block cam
        }else if (savePref.getSwitches().mainswitch){
            Intent not = new Intent(context, appService.class);
            not.setAction("camnotification");
            context.startService(not);
        }
    }
}
