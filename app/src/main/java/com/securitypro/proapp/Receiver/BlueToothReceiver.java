package com.securitypro.proapp.Receiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.securitypro.proapp.Service.appService;
import com.securitypro.proapp.Utility.SavePref;

/**
 * Created by Chetna on 28-Mar-18.
 */

public class BlueToothReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        SavePref savePref = new SavePref(context);

        if ("android.bluetooth.device.action.ACL_CONNECTED".equals(action)) {

            Toast.makeText(context, "bt connected", Toast.LENGTH_SHORT).show();

            if (savePref.getSwitches().mainswitch && savePref.getSwitches().bt_switch) {
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                }
            }else if (savePref.getSwitches().mainswitch){
                Intent not = new Intent(context, appService.class);
                not.setAction("bluetooth");
                context.startService(not);
            }

        }

        /*if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            if(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
                    == BluetoothAdapter.STATE_OFF) {
                // Bluetooth is disconnected, do handling here
                //Toast.makeText(context, "bt off", Toast.LENGTH_SHORT).show();
                String b = "on";
            }else {
                if (savePref.getSwitches().mainswitch && savePref.getSwitches().bt_switch) {
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.disable();
                    }
                }else if (savePref.getSwitches().mainswitch){
                    Intent not = new Intent(context, appService.class);
                    not.setAction("bluetooth");
                    context.startService(not);
                }
            }
        }*/

    }
}
