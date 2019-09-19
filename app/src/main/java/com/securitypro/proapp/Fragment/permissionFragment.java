package com.securitypro.proapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.securitypro.proapp.R;


public class permissionFragment extends Fragment implements View.OnClickListener {

    ImageView cam_perm, phonestate_perm, mic_perm, READHISTORY_PERM, SENDSMS_PERM, READSMS_PERM, LOC_PERM, call_perm;

    public permissionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_permission, container, false);

        cam_perm = view.findViewById(R.id.cam_perm);
        phonestate_perm = view.findViewById(R.id.phonestate_perm);
        mic_perm = view.findViewById(R.id.mic_perm);
        READHISTORY_PERM = view.findViewById(R.id.READHISTORY_PERM);
        SENDSMS_PERM = view.findViewById(R.id.SENDSMS_PERM);
        READSMS_PERM = view.findViewById(R.id.READSMS_PERM);
        LOC_PERM = view.findViewById(R.id.LOC_PERM);
        call_perm = view.findViewById(R.id.call_perm);

        cam_perm.setOnClickListener(this);
        phonestate_perm.setOnClickListener(this);
        mic_perm.setOnClickListener(this);
        READHISTORY_PERM.setOnClickListener(this);
        SENDSMS_PERM.setOnClickListener(this);
        READSMS_PERM.setOnClickListener(this);
        LOC_PERM.setOnClickListener(this);
        call_perm.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.cam_perm:
                AppsByPermission("CAMERA");
                break;
            case R.id.phonestate_perm:
                AppsByPermission("READ_PHONE_STATE");
                break;
            case R.id.mic_perm:
                AppsByPermission("RECORD_AUDIO");
                break;
            case R.id.READHISTORY_PERM:
                AppsByPermission("READ_HISTORY_BOOKMARKS");
                break;
            case R.id.SENDSMS_PERM:
                AppsByPermission("SEND_SMS");
                break;
            case R.id.READSMS_PERM:
                AppsByPermission("READ_SMS");
                break;
            case R.id.LOC_PERM:
                AppsByPermission("ACCESS_FINE_LOCATION");
                break;
            case R.id.call_perm:
                AppsByPermission("CALL_PHONE");
                break;
        }

    }

    private void AppsByPermission(String s) {

        Toast.makeText(getActivity(), "found", Toast.LENGTH_SHORT).show();

        Bundle appInfo = new Bundle();
        appInfo.putString("perm", s);

        Fragment fragment;
        fragment = new appsByPermFragment();
        fragment.setArguments(appInfo);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_apsbyperm, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();


    }

}