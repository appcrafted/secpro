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

import com.securitypro.proapp.Adapter.HiddenAdapter;
import com.securitypro.proapp.Model.Application;
import com.securitypro.proapp.R;

import com.securitypro.proapp.Utility.Commons;
import java.util.ArrayList;
import java.util.List;

public class hiddenFragment extends Fragment {

    HiddenAdapter adapter;
    List<Application> hidden = new ArrayList();
    RecyclerView hidden_recycle;

    public hiddenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hidden, container, false);

        hidden_recycle = view.findViewById(R.id.hidden_recycle);
        if (Build.VERSION.SDK_INT >= 21) {
            hidden_recycle.setNestedScrollingEnabled(true);
        }
        hidden_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hidden = Commons.getHiddenApps(getActivity());
        adapter = new HiddenAdapter(getActivity(), hidden);
        hidden_recycle.setAdapter(adapter);

    }


}