package com.securitypro.proapp.Utility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.securitypro.proapp.Model.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Chetna on 10-Mar-18.
 */

public class Commons {

    Context context;

    public Commons(Context context) {
        this.context = context;
    }


    public static List<Application> getApplicationListAll(Context context) {
        List<Application> list = new ArrayList();
        new Intent("android.intent.action.MAIN", null).addCategory("android.intent.category.LAUNCHER");
        PackageManager packageManager = context.getPackageManager();
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(4096)) {
            try {
                ApplicationInfo ai = packageManager.getApplicationInfo(packageInfo.packageName, 0);
                if (!((ai.flags & 1) != 0 || packageInfo.packageName.equals(context.getPackageName()) || packageManager.getLaunchIntentForPackage(packageInfo.applicationInfo.packageName) == null)) {
                    list.add(new Application((String) packageManager.getApplicationLabel(ai), packageInfo.packageName, packageInfo.applicationInfo.loadIcon(packageManager)));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static ArrayList<Application> getHiddenApps(Context context) {
        ArrayList<Application> hidden = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(4096)) {
            try {
                ApplicationInfo ai = packageManager.getApplicationInfo(packageInfo.packageName, 0);
                if ((ai.flags & 1) == 0 && packageManager.getLaunchIntentForPackage(packageInfo.applicationInfo.packageName) == null) {
                    hidden.add(new Application((String) packageManager.getApplicationLabel(ai), packageInfo.packageName, packageInfo.applicationInfo.loadIcon(packageManager)));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return hidden;
    }

    public static List<Application> getApplicationListAccordingPermission(String permission, Context context) {
        List<Application> applications = new ArrayList();
        PackageManager p = context.getPackageManager();
        for (PackageInfo packageInfo : p.getInstalledPackages(4096)) {
            if (packageInfo.requestedPermissions != null) {
                String[] strArr = packageInfo.requestedPermissions;
                int length = strArr.length;
                int i = 0;
                while (i < length) {
                    if (permission.equals(strArr[i])) {
                        try {
                            ApplicationInfo ai = p.getApplicationInfo(packageInfo.packageName, 0);
                            if ((ai.flags & 1) == 0) {
                                applications.add(new Application((String) p.getApplicationLabel(ai), packageInfo.packageName, packageInfo.applicationInfo.loadIcon(context.getPackageManager())));
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        i++;
                    }
                }
            }
        }
        return applications;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
                : false;
    }

    public static void getapp(Context context){
        final PackageManager pm = context.getPackageManager();
//get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Installed package :" + packageInfo.packageName);
            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }
    }

    public static boolean hasPermission(String permission, String appid, Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(appid, 4096);
            if (info.requestedPermissions == null) {
                return false;
            }
            for (String p : info.requestedPermissions) {
                if (p.equals(permission)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<String> getAppPermissions(Context context, String packageName) {
        try {
            return Arrays.asList(context.getPackageManager().getPackageInfo(packageName, 4096).requestedPermissions);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }




}
