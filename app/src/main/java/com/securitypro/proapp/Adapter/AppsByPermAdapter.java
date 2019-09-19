package com.securitypro.proapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.securitypro.proapp.Model.Application;
import com.securitypro.proapp.R;

import java.util.List;

/**
 * Created by Chetna on 30-Mar-18.
 */

public class AppsByPermAdapter extends RecyclerView.Adapter<AppsByPermAdapter.MyAppsByPermApp> {

    Context context;
    List<Application> AppsByPermlist;

    public AppsByPermAdapter(Context context, List<Application> AppsByPermlist) {
        this.context = context;
        this.AppsByPermlist = AppsByPermlist;
    }

    @Override
    public AppsByPermAdapter.MyAppsByPermApp onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app, parent, false);
        return new AppsByPermAdapter.MyAppsByPermApp(itemView);
    }

    @Override
    public void onBindViewHolder(AppsByPermAdapter.MyAppsByPermApp holder, final int position) {
        holder.appname.setText(AppsByPermlist.get(position).get_name());
        holder.packagename.setText(AppsByPermlist.get(position).get_package());
        holder.icon.setImageDrawable(AppsByPermlist.get(position).get_icon());
        holder.danger.setBackgroundColor(Color.parseColor("#8b0000"));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",AppsByPermlist.get(position).get_package(), null);
                intent.setData(uri);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return AppsByPermlist.size();
    }

    public class MyAppsByPermApp extends RecyclerView.ViewHolder  {

        ImageView icon, danger;
        TextView appname, packagename;
        LinearLayout card;

        public MyAppsByPermApp(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            danger = itemView.findViewById(R.id.danger);
            appname = itemView.findViewById(R.id.appname);
            packagename = itemView.findViewById(R.id.packagename);
            card = itemView.findViewById(R.id.card);


        }

    }
}
