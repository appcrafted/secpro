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

import com.securitypro.proapp.Adapter.AllAdapter;
import com.securitypro.proapp.Model.Application;
import com.securitypro.proapp.R;
import com.securitypro.proapp.Utility.Commons;

import java.util.ArrayList;
import java.util.List;

public class allFragment extends Fragment {

    AllAdapter adapter;
    List<Application> allapp = new ArrayList();
    RecyclerView all_recycle;

    public allFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        all_recycle = view.findViewById(R.id.all_recycle);
        if (Build.VERSION.SDK_INT >= 21) {
            all_recycle.setNestedScrollingEnabled(true);
        }
        all_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allapp = Commons.getApplicationListAll(getActivity());
        adapter = new AllAdapter(getActivity(), allapp);
        all_recycle.setAdapter(adapter);
    }

}