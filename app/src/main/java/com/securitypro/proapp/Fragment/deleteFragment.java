package com.securitypro.proapp.Fragment;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.securitypro.proapp.Adapter.DeleteAdapter;
import com.securitypro.proapp.R;
import com.securitypro.proapp.Utility.Commons;

import java.util.ArrayList;
import java.util.List;


public class deleteFragment extends Fragment {

    DeleteAdapter adapter;
    List<String> permissionlist = new ArrayList();
    RecyclerView permission_recycle;
    String appName, pkgName;
    TextView tv_app_name;
    ImageView bt_uninstall_app, bt_ignore_app, img_icon;

    public deleteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete, container, false);

        if (getArguments() != null) {
            if (getArguments().containsKey("appName")) {
                appName = getArguments().getString("appName", "appName");
            }

            if (getArguments().containsKey("PkgName")) {
                pkgName = getArguments().getString("PkgName", "PkgName");
            }

        }

        permission_recycle = view.findViewById(R.id.rv_permissions);

        tv_app_name = view.findViewById(R.id.tv_app_name);
        tv_app_name.setText(appName);

        bt_ignore_app = view.findViewById(R.id.bt_ignore_app);
        bt_uninstall_app = view.findViewById(R.id.bt_uninstall_app);

        bt_uninstall_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+pkgName));
                startActivity(intent);

            }
        });

        bt_ignore_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        img_icon = view.findViewById(R.id.bg_icon_app);

        try {

            Drawable icon = getActivity().getPackageManager().getApplicationIcon(pkgName);
            img_icon.setImageDrawable(icon);

        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 21) {
            permission_recycle.setNestedScrollingEnabled(true);
        }
        permission_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            permissionlist = Commons.getAppPermissions(getActivity(), pkgName);
        }catch (Exception e){
            permissionlist.add("No permission");
        }

        adapter = new DeleteAdapter(getActivity(), permissionlist);
        permission_recycle.setAdapter(adapter);

    }


}