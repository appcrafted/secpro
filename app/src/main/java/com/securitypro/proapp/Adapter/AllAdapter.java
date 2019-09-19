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
 * Created by Chetna on 27-Mar-18.
 */

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.MyAllApp> {

    Context context;
    List<Application> applist;

    public AllAdapter(Context context, List<Application> applist) {
        this.context = context;
        this.applist = applist;
    }

    @Override
    public AllAdapter.MyAllApp onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app, parent, false);
        return new AllAdapter.MyAllApp(itemView);
    }

    @Override
    public void onBindViewHolder(AllAdapter.MyAllApp holder, int position) {
        holder.appname.setText(applist.get(position).get_name());
        holder.packagename.setText(applist.get(position).get_package());
        holder.icon.setImageDrawable(applist.get(position).get_icon());
        holder.danger.setBackgroundColor(Color.parseColor("#8b0000"));

    }

    @Override
    public int getItemCount() {
        return applist.size();
    }

    public class MyAllApp extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView icon, danger;
        TextView appname, packagename;
        LinearLayout card;

        public MyAllApp(View itemView) {
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
            appInfo.putString("appName", applist.get(getAdapterPosition()).get_name());
            appInfo.putString("PkgName", applist.get(getAdapterPosition()).get_package());

            Fragment fragment;
            fragment = new deleteFragment();
            fragment.setArguments(appInfo);
            FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container_all, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null).commit();

        }
    }
}
