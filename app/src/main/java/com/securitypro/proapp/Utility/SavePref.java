package com.securitypro.proapp.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.securitypro.proapp.Model.Licence;
import com.securitypro.proapp.Model.SwitchModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SavePref
{

    SharedPreferences local;
    Context context;
    public SavePref(Context context)
    {
        this.context=context;
        local=context.getSharedPreferences("local",0);
    }

    public String getEmail()
    {
        return local.getString("UserEmail", "");
    }
    public void setEmail(String email)
    {
        SharedPreferences.Editor editor=local.edit();
        editor.putString("UserEmail",email);
        editor.commit();
    }



    public Boolean getDeviceAdmin()
    {
        return local.getBoolean("DeviceAdmin", false);
    }
    public void setDeviceAdmin(Boolean value)
    {
        SharedPreferences.Editor editor=local.edit();
        editor.putBoolean("DeviceAdmin",value);
        editor.commit();
    }


//    ------------------------------------
//    -----LICENCE------------------------
//    ------------------------------------

    public void setLicense(Licence licence)
    {
        SharedPreferences.Editor editor=local.edit();
        editor.putString("_id", licence._id);
        editor.putString("key",licence.key);
        editor.putString("ad", licence.ad);
        editor.putString("ed", licence.ed);
        editor.putString("deviceid", licence.deviceid);
        editor.apply();
    }

    public Licence getLicense()
    {
        String _id=local.getString("_id", "");
        String key=local.getString("key", "");
        String ad=local.getString("ad","");
        String ed=local.getString("ed","");
        String deviceid=local.getString("deviceid","");
        return new Licence(_id,key,ad,ed,deviceid);
    }

    public boolean checkLicense()
    {

        return true;
        /*Licence licence= getLicense();

        String mykey = licence.key;
        String ed = licence.ed;

        //int ed = Integer.parseInt(licence.ed);
        if("".equals(mykey))
        {
            return  false;
        }
        else
        {
            try
            {
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                Date date = format.parse(licence.ed);
                Date now=new Date();

                if(now.after(date))
                {
                    setLicense(new Licence("","","","",""));
                    return false;
                }
                return true;
            }
            catch (Exception e)
            {
                setLicense(new Licence("","","","",""));
                return false;
            }
        }*/
    }

    public void setSwitches(SwitchModel switches)
    {
        SharedPreferences.Editor editor=local.edit();
        editor.putBoolean("mainswitch", switches.mainswitch);
        editor.putBoolean("cam_switch",switches.cam_switch);
        editor.putBoolean("mic_switch", switches.mic_switch);
        editor.putBoolean("wifi_switch", switches.wifi_switch);
        editor.putBoolean("bt_switch", switches.bt_switch);
        editor.apply();
    }

    public SwitchModel getSwitches()
    {
        Boolean mainswitch=local.getBoolean("mainswitch", false);
        Boolean cam_switch=local.getBoolean("cam_switch", false);
        Boolean mic_switch=local.getBoolean("mic_switch",false);
        Boolean wifi_switch=local.getBoolean("wifi_switch",false);
        Boolean bt_switch=local.getBoolean("bt_switch",false);
        return new SwitchModel(mainswitch,cam_switch,mic_switch,wifi_switch,bt_switch);
    }





}
