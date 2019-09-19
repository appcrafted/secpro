package com.securitypro.proapp.Utility;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class PostHttp extends AsyncTask<Void,Void,String> {

    Map<String,String> list;
    Responsable responsable;
    String url_string="";

    public PostHttp(String url, Map<String,String> list, Responsable responsable)
    {
        url_string=url;
        this.list=list;
        this.responsable=responsable;
    }

    @Override
    protected String doInBackground(Void... params)
    {
        try
        {
            URL url = new URL(url_string);
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,String> pair: list.entrySet())
            {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String reply=in.readLine();
            return reply;
        }
        catch (Exception e)
        {

        }

        return null;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        responsable.done(s);
    }
}