package com.asefactory.ase21.filereloader.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.asefactory.ase21.filereloader.shared_prefs.SharePref;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            Log.d("mazafaka", "onReceive: ");
            Toast.makeText(context, "Загрузился бут ресивер", Toast.LENGTH_SHORT).show();
            if (SharePref.getInstance(context).isitWorks()){
                DownloadBrodcastReceiver.downloadFile(context);
            }
        }
    }
}
