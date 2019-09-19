package com.securitypro.proapp.Fragment;

import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.securitypro.proapp.Model.SwitchModel;
import com.securitypro.proapp.R;
import com.securitypro.proapp.Receiver.DeviceAdminSampleReceiver;
import com.securitypro.proapp.Utility.SavePref;

import java.util.Iterator;
import java.util.List;


public class appSecureFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    DevicePolicyManager mDevicePolicyManager;
    ComponentName deviceAdminSample;
    SavePref savePref;

    TextView text_secure_mode, text_camera_mode, text_mic_mode, text_wifi_mode, text_bluetooth_mode;
    Switch main_switch, cam_switch, mic_switch, wifi_switch, bluetooth_switch;

    public appSecureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vassive, container, false);

        text_secure_mode = view.findViewById(R.id.text_secure_mode);
        text_camera_mode = view.findViewById(R.id.text_camera_mode);
        text_mic_mode = view.findViewById(R.id.text_mic_mode);
        text_wifi_mode = view.findViewById(R.id.text_wifi_mode);
        text_bluetooth_mode = view.findViewById(R.id.text_bluetooth_mode);

        main_switch = view.findViewById(R.id.main_switch);
        cam_switch = view.findViewById(R.id.cam_switch);
        mic_switch = view.findViewById(R.id.mic_switch);
        wifi_switch = view.findViewById(R.id.wifi_switch);
        bluetooth_switch = view.findViewById(R.id.bluetooth_switch);

        savePref = new SavePref(getActivity());
        mDevicePolicyManager = (DevicePolicyManager) getActivity().getSystemService(Context.DEVICE_POLICY_SERVICE);

        checkDeviceAdmin();

        if (savePref.getSwitches().mainswitch) {
            enableswitch();
            getSwitchState();

        } else {
            dissableswitch();
            getSwitchState();
        }

        main_switch.setOnCheckedChangeListener(this);
        cam_switch.setOnCheckedChangeListener(this);
        mic_switch.setOnCheckedChangeListener(this);
        wifi_switch.setOnCheckedChangeListener(this);
        bluetooth_switch.setOnCheckedChangeListener(this);


        return view;
    }

    private void getSwitchState() {
        main_switch.setChecked(savePref.getSwitches().mainswitch);
        cam_switch.setChecked(savePref.getSwitches().cam_switch);
        mic_switch.setChecked(savePref.getSwitches().mic_switch);
        wifi_switch.setChecked(savePref.getSwitches().wifi_switch);
        bluetooth_switch.setChecked(savePref.getSwitches().bt_switch);
    }

    private void dissableswitch() {
        cam_switch.setEnabled(false);
        mic_switch.setEnabled(false);
        wifi_switch.setEnabled(false);
        bluetooth_switch.setEnabled(false);
    }

    private void enableswitch() {
        cam_switch.setEnabled(true);
        mic_switch.setEnabled(true);
        wifi_switch.setEnabled(true);
        bluetooth_switch.setEnabled(true);
    }

    private void disableCam() {
        mDevicePolicyManager.setCameraDisabled(deviceAdminSample, true);
    }

    private void enableCam() {
        mDevicePolicyManager.setCameraDisabled(deviceAdminSample, false);
    }

    private ComponentName getActiveComponentName() {

        ComponentName componentName = null;
        List<ComponentName> activeComponentList = mDevicePolicyManager.getActiveAdmins();
        Iterator<ComponentName> iterator = activeComponentList.iterator();
        while (iterator.hasNext()) {
            componentName = (ComponentName) iterator.next();
        }
        return componentName;
    }

    private void checkDeviceAdmin() {
        deviceAdminSample = new ComponentName(getActivity(), DeviceAdminSampleReceiver.class);
        if (!savePref.getDeviceAdmin()) {
            AlertBoxForDAdmin();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.main_switch:
                if (b) {
                    main_switch.setChecked(true);
                    savePref.setSwitches(new SwitchModel(true, savePref.getSwitches().cam_switch, savePref.getSwitches().mic_switch, savePref.getSwitches().wifi_switch, savePref.getSwitches().bt_switch));
                    enableswitch();
                } else {
                    main_switch.setChecked(false);
                    savePref.setSwitches(new SwitchModel(false, savePref.getSwitches().cam_switch, savePref.getSwitches().mic_switch, savePref.getSwitches().wifi_switch, savePref.getSwitches().bt_switch));
                    dissableswitch();
                }
                break;

            case R.id.cam_switch:
                if (b) {
                    cam_switch.setChecked(true);
                    savePref.setSwitches(new SwitchModel(savePref.getSwitches().mainswitch, true, savePref.getSwitches().mic_switch, savePref.getSwitches().wifi_switch, savePref.getSwitches().bt_switch));
                    disableCam();
                } else {
                    cam_switch.setChecked(false);
                    savePref.setSwitches(new SwitchModel(savePref.getSwitches().mainswitch, false, savePref.getSwitches().mic_switch, savePref.getSwitches().wifi_switch, savePref.getSwitches().bt_switch));
                    enableCam();
                }
                break;

            case R.id.mic_switch:
                if (b) {
                    mic_switch.setChecked(true);
                    savePref.setSwitches(new SwitchModel(savePref.getSwitches().mainswitch, savePref.getSwitches().cam_switch, true, savePref.getSwitches().wifi_switch, savePref.getSwitches().bt_switch));

                    micmute();
                } else {
                    mic_switch.setChecked(false);
                    savePref.setSwitches(new SwitchModel(savePref.getSwitches().mainswitch, savePref.getSwitches().cam_switch, false, savePref.getSwitches().wifi_switch, savePref.getSwitches().bt_switch));

                    micunmute();
                }
                break;

            case R.id.wifi_switch:
                if (b) {
                    wifi_switch.setChecked(true);
                    savePref.setSwitches(new SwitchModel(savePref.getSwitches().mainswitch, savePref.getSwitches().cam_switch, savePref.getSwitches().mic_switch, true, savePref.getSwitches().bt_switch));

                    WifiManager wifiM = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    if (wifiM != null) {
                        wifiM.setWifiEnabled(false);
                    }
                } else {
                    wifi_switch.setChecked(false);
                    savePref.setSwitches(new SwitchModel(savePref.getSwitches().mainswitch, savePref.getSwitches().cam_switch, savePref.getSwitches().mic_switch, false, savePref.getSwitches().bt_switch));

                }
                break;

            case R.id.bluetooth_switch:
                if (b) {
                    bluetooth_switch.setChecked(true);
                    savePref.setSwitches(new SwitchModel(savePref.getSwitches().mainswitch, savePref.getSwitches().cam_switch, savePref.getSwitches().mic_switch, savePref.getSwitches().wifi_switch, true));

                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.disable();
                    }
                } else {
                    bluetooth_switch.setChecked(false);
                    savePref.setSwitches(new SwitchModel(savePref.getSwitches().mainswitch, savePref.getSwitches().cam_switch, savePref.getSwitches().mic_switch, savePref.getSwitches().wifi_switch, false));

                }
                break;
        }
    }

    private void micmute() {

        final AudioManager aM = (AudioManager) getActivity().getSystemService("audio");
        aM.setMode(2);
        aM.setMicrophoneMute(true);
        //MicMute.this.status.setText("Muted");
    }

    private void micunmute() {
        final AudioManager aM = (AudioManager) getActivity().getSystemService("audio");
        if (aM.isMicrophoneMute()) {
            aM.setMode(2);
            aM.setMicrophoneMute(false);
            //MicMute.this.status.setText("Unmuted");
            return;
        }
    }

    private void AlertBoxForDAdmin() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Enable Device Admin");
        alertDialogBuilder.setMessage(R.string.add_admin_extra_app_text);
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        System.out.println("KEY_CB_DEVICE_ADMIN enable");
                        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdminSample);
                        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                                getActivity().getString(R.string.add_admin_extra_app_text));
                        startActivityForResult(intent, 1);
                        savePref.setDeviceAdmin(true);
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}