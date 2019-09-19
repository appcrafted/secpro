package com.securitypro.proapp.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterKey extends AppCompatActivity {

    private EditText input_key;
    private TextInputLayout input_layout_key ;
    private Button btn_verify;
    SavePref savePref;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_key);

        input_key = (EditText) findViewById(R.id.input_key);
        input_layout_key = (TextInputLayout)findViewById(R.id.input_layout_key);
        btn_verify = (Button)findViewById(R.id.btn_verify);


        savePref = new SavePref(this);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Utility.haveNetworkConnection(RegisterKey.this)) {
                    if (validatekey()) {

                        progressDialog = new ProgressDialog(RegisterKey.this);
                        progressDialog.setMessage("Please wait...");
                        progressDialog.show();

                        String mykey = input_key.getText().toString().trim();

                        @SuppressLint("HardwareIds")
                        String androidId = Settings.Secure.getString(getContentResolver(),
                                Settings.Secure.ANDROID_ID);

                        Map<String, String> map = new HashMap<>();
                        map.put("key", mykey);
                        map.put("deviceid", androidId);
                        new PostHttp("https://rudraumantispy.herokuapp.com/verifyKey", map, new Responsable() {
                            @Override
                            public void done(String reply) {

                                if (reply == null){
                                    progressDialog.dismiss();
                                    Toast.makeText(RegisterKey.this, "Enter Valid key", Toast.LENGTH_SHORT).show();
                                }else {

                                    try {
                                        JSONObject obj = new JSONObject(reply);
                                        JSONObject obj1 = obj.getJSONObject("doc");

                                        String _id = obj1.getString("_id");
                                        String key = obj1.getString("key");
                                        String deviceid = obj1.getString("deviceid");
                                        String ad = obj1.getString("ad");
                                        String ed = obj1.getString("ed");

                                        savePref.setLicense(new Licence(_id, key, ad, ed, deviceid));
                                        if (savePref.checkLicense()) {
                                            progressDialog.dismiss();
                                            startActivity(new Intent(RegisterKey.this, MainActivity.class));
                                            finish();
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(RegisterKey.this, "Licence Expired", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        progressDialog.dismiss();
                                        Toast.makeText(RegisterKey.this, "" + e, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                        }).execute();
                    }
                }else {
                    Toast.makeText(RegisterKey.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validatekey() {
        if (input_key.getText().toString().trim().isEmpty()) {
            input_layout_key.setError("please Enter Valid Registration Key");
            return false;
        } else {
            //input_layout_key.setErrorEnabled(false);
            return true;
        }

        //return true;
    }
}

//
//    {
//        "doc": {
//        "_id": "5a3d04fc4168b4062884002e",
//        "key": "SyrbSYqGf",
//        "_creator": "5a3baa8db4d4ae0b0c1b3389",
//        "__v": 0,
//        "year": 2,
//        "deviceid": "nkjjkhkjhg",
//        "ed": "2019-12-22T13:13:57.109Z",
//        "ad": "2017-12-22T13:13:57.109Z",
//        "active": true
//        }
//    }