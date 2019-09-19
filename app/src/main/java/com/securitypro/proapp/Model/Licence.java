package com.securitypro.proapp.Model;

/**
 * Created by Chetna on 23-Dec-17.
 */

public class Licence {
    public String _id ;
    public String key;
    public String ad;
    public String ed;
    public String deviceid;

    public Licence(String _id, String key, String ad, String ed, String deviceid) {
        this._id = _id;
        this.key = key;
        this.ad = ad;
        this.ed = ed;
        this.deviceid = deviceid;
    }
}
