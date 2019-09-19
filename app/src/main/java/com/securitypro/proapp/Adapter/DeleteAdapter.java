package com.securitypro.proapp.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.securitypro.proapp.R;

import java.util.List;

/**
 * Created by Chetna on 27-Mar-18.
 */

public class DeleteAdapter extends RecyclerView.Adapter<DeleteAdapter.MyAllPermission> {

    Context context;
    List<String> permissionlist ;

    public DeleteAdapter(Context context, List<String> permissionlist) {
        this.context = context;
        this.permissionlist = permissionlist;
    }

    @Override
    public DeleteAdapter.MyAllPermission onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app, parent, false);
        return new DeleteAdapter.MyAllPermission(itemView);
    }

    @Override
    public void onBindViewHolder(DeleteAdapter.MyAllPermission holder, int position) {
        holder.appname.setText(setPermissionTitle(permissionlist.get(position)));
        holder.packagename.setText(setPermissionMessage(permissionlist.get(position)));
        holder.icon.setImageResource(setPermissionIcon(permissionlist.get(position)));
        holder.danger.setBackgroundColor(Color.parseColor("#8b0000"));

    }

    @Override
    public int getItemCount() {
        return permissionlist.size();
    }

    public String setPermissionMessage(String permissionName) {
        String message = permissionName;
        Resources resources = this.context.getResources();
        if (permissionName.contains("READ_PHONE_STATE")) {
            return resources.getString(R.string.read_phone_message);
        }
        if (permissionName.contains("ACCESS_FINE_LOCATION")) {
            return resources.getString(R.string.access_fine_message);
        }
        if (permissionName.contains("READ_SMS")) {
            return resources.getString(R.string.read_sms_message);
        }
        if (permissionName.contains("WRITE_SMS")) {
            return resources.getString(R.string.write_sms_message);
        }
        if (permissionName.contains("SEND_SMS")) {
            return resources.getString(R.string.send_sms_message);
        }
        if (permissionName.contains("READ_HISTORY_BOOKMARKS")) {
            return resources.getString(R.string.read_history_message);
        }
        if (permissionName.contains("WRITE_HISTORY_BOOKMARKS")) {
            return resources.getString(R.string.write_history_message);
        }
        if (permissionName.contains("CALL_PHONE")) {
            return resources.getString(R.string.call_phone_message);
        }
        if (permissionName.contains("PROCESS_OUTGOING_CALLS")) {
            return resources.getString(R.string.outgoing_phone_message);
        }
        if (permissionName.contains("RECORD_AUDIO")) {
            return resources.getString(R.string.record_audio_message);
        }
        if (permissionName.contains("CAMERA")) {
            return resources.getString(R.string.camera_message);
        }
        return message;
    }

    public String setPermissionTitle(String permissionName) {
        String message = permissionName;
        Resources resources = this.context.getResources();
        if (permissionName.contains("READ_PHONE_STATE")) {
            return resources.getString(R.string.phone_data_shared);
        }
        if (permissionName.contains("ACCESS_FINE_LOCATION")) {
            return resources.getString(R.string.location_shared);
        }
        if (permissionName.contains("READ_SMS")) {
            return resources.getString(R.string.read_your_sms);
        }
        if (permissionName.contains("WRITE_SMS")) {
            return resources.getString(R.string.write_sms_title);
        }
        if (permissionName.contains("SEND_SMS")) {
            return resources.getString(R.string.send_sms_title);
        }
        if (permissionName.contains("READ_HISTORY_BOOKMARKS")) {
            return resources.getString(R.string.read_history_bookmark_title);
        }
        if (permissionName.contains("WRITE_HISTORY_BOOKMARKS")) {
            return resources.getString(R.string.write_history_bookmark_title);
        }
        if (permissionName.contains("CALL_PHONE")) {
            return resources.getString(R.string.can_make_call_title);
        }
        if (permissionName.contains("PROCESS_OUTGOING_CALLS")) {
            return resources.getString(R.string.outgoing_calls_title);
        }
        if (permissionName.contains("RECORD_AUDIO")) {
            return resources.getString(R.string.record_audio_title);
        }
        if (permissionName.contains("CAMERA")) {
            return resources.getString(R.string.access_camera_title);
        }
        return message;
    }

    public int setPermissionIcon(String permissionName) {
        if (permissionName.contains("READ_PHONE_STATE")) {
            return R.drawable.phone_icon;
        }
        if (permissionName.contains("ACCESS_FINE_LOCATION")) {
            return R.drawable.fine_location_icon;
        }
        if (permissionName.contains("READ_SMS")) {
            return R.drawable.read_sms;
        }
        if (permissionName.contains("WRITE_SMS")) {
            return R.drawable.send_sms;
        }
        if (permissionName.contains("SEND_SMS")) {
            return R.drawable.send_sms;
        }
        if (permissionName.contains("READ_HISTORY_BOOKMARKS")) {
            return R.drawable.history_icon;
        }
        if (permissionName.contains("WRITE_HISTORY_BOOKMARKS")) {
            return R.drawable.history_icon;
        }
        if (permissionName.contains("CALL_PHONE")) {
            return R.drawable.phone_icon;
        }
        if (permissionName.contains("PROCESS_OUTGOING_CALLS")) {
            return R.drawable.phone_icon;
        }
        if (permissionName.contains("RECORD_AUDIO")) {
            return R.drawable.record_audio_icon;
        }
        if (permissionName.contains("CAMERA")) {
            return R.drawable.camera_icon;
        }
        return R.drawable.ic_launcher_background;
    }

    public class MyAllPermission extends RecyclerView.ViewHolder {

        ImageView icon, danger;
        TextView appname, packagename;

        public MyAllPermission(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            danger = itemView.findViewById(R.id.danger);
            appname = itemView.findViewById(R.id.appname);
            packagename = itemView.findViewById(R.id.packagename);
        }
    }


}
