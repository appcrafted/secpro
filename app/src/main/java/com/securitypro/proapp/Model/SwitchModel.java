package com.securitypro.proapp.Model;

/**
 * Created by Chetna on 01-Apr-18.
 */

public class SwitchModel {
    public Boolean mainswitch ;
    public Boolean cam_switch;
    public Boolean mic_switch;
    public Boolean wifi_switch;
    public Boolean bt_switch;

    public SwitchModel(Boolean mainswitch, Boolean cam_switch, Boolean mic_switch, Boolean wifi_switch, Boolean bt_switch) {
        this.mainswitch = mainswitch;
        this.cam_switch = cam_switch;
        this.mic_switch = mic_switch;
        this.wifi_switch = wifi_switch;
        this.bt_switch = bt_switch;
    }
}
