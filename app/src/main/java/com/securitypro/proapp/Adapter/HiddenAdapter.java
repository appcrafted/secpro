package com.securitypro.proapp.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.securitypro.proapp.Activity.MainActivity;
import com.securitypro.proapp.Fragment.deleteFragment;
import com.securitypro.proapp.Model.Application;
import com.securitypro.proapp.R;

import java.util.List;

/**
 * Created by kdblue on 18/12/17.
 */

public class HiddenAdapter extends RecyclerView.Adapter<HiddenAdapter.MyhiddenApp> {

    Context context;
    List<Application> hiddenlist;

    public HiddenAdapter(Context context, List<Application> hiddenlist) {
        this.context = context;
        this.hiddenlist = hiddenlist;
    }

    @Override
    public MyhiddenApp onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app, parent, false);
        return new HiddenAdapter.MyhiddenApp(itemView);
    }

    @Override
    public void onBindViewHolder(MyhiddenApp holder, int position) {
        holder.appname.setText(hiddenlist.get(position).get_name());
        holder.packagename.setText(hiddenlist.get(position).get_package());
        holder.icon.setImageDrawable(hiddenlist.get(position).get_icon());
        holder.danger.setBackgroundColor(Color.parseColor("#8b0000"));

    }

    @Override
    public int getItemCount() {
        return hiddenlist.size();
    }

    public class MyhiddenApp extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView icon, danger;
        TextView appname, packagename;
        LinearLayout card;

        public MyhiddenApp(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            danger = itemView.findViewById(R.id.danger);
            appname = itemView.findViewById(R.id.appname);
            packagename = itemView.findViewById(R.id.packagename);
            card = itemView.findViewById(R.id.card);

            card.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {

            Bundle appInfo = new Bundle();
            appInfo.putString("appName", hiddenlist.get(getAdapterPosition()).get_name());
            appInfo.putString("PkgName", hiddenlist.get(getAdapterPosition()).get_package());

            Fragment fragment;
            fragment = new deleteFragment();
            fragment.setArguments(appInfo);
            FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container_hidden, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null).commit();

        }
    }
}
