package com.securitypro.proapp.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.securitypro.proapp.Adapter.AppsByPermAdapter;
import com.securitypro.proapp.Model.Application;
import com.securitypro.proapp.R;
import com.securitypro.proapp.Utility.Commons;

import java.util.ArrayList;
import java.util.List;


public class appsByPermFragment extends Fragment {

    AppsByPermAdapter adapter;
    List<Application> appsbyPerm = new ArrayList();
    RecyclerView appsByPerm_recycle;
    String perm;

    public appsByPermFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apps_by_perm, container, false);

        if (getArguments() != null) {
            if (getArguments().containsKey("perm")) {
                perm = getArguments().getString("perm", "perm");
            }

        }

        appsByPerm_recycle = view.findViewById(R.id.appsByPerm_recycle);
        if (Build.VERSION.SDK_INT >= 21) {
            appsByPerm_recycle.setNestedScrollingEnabled(true);
        }
        appsByPerm_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //appsbyPerm = Commons.getApplicationListAccordingPermission("android.permission."+perm, getActivity());
        appsbyPerm = Commons.getApplicationListAll( getActivity());
        adapter = new AppsByPermAdapter(getActivity(), appsbyPerm);
        appsByPerm_recycle.setAdapter(adapter);

    }


}