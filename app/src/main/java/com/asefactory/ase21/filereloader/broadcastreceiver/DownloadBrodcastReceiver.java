package com.asefactory.ase21.filereloader.broadcastreceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.asefactory.ase21.filereloader.network.DownloadAPI;
import com.asefactory.ase21.filereloader.shared_prefs.SharePref;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownloadBrodcastReceiver extends BroadcastReceiver {

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    @Override
    public void onReceive(final Context context, Intent intent) {
//        //Get notification manager to manage/send notifications
//
//        //Intent to invoke app when click on notification.
//        //In this sample, we want to start/launch this sample app when user clicks on notification
//        Intent intentToRepeat = new Intent(context, MainActivity.class);
//        //set flag to restart/relaunch the app
//        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        //Pending intent to handle launch of Activity in intent above
//        PendingIntent pendingIntent =
//                PendingIntent.getActivity(context, 100, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                .setContentIntent(pendingIntent)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(SharePref.getInstance(context).getDownloadUrl())
//                .setContentText("Пришло время подать показания счетчиков!")
//                .setAutoCancel(true);
//
//        NotificationManager notificationManager =
//                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
//            assert notificationManager != null;
//            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//        notificationManager.notify(100, builder.build());
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://google.com/");

        Retrofit retrofit = builder.build();

        DownloadAPI fileDownloadClient = retrofit.create(DownloadAPI.class);

        String url = SharePref.getInstance(context).getDownloadUrl();
        Call<ResponseBody> call = fileDownloadClient.downloadFile(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                boolean b = writeResponseBodyToDisk(context, body);
                if (b){
                    Toast.makeText(context, "file downloaded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "file downloaded, but not saved", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("ase21", "выполнилось уведосление");

    }
    public static void SetAlarm(Context context, int hour){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long intervalInMillis = cal.getTimeInMillis();
        //получаем алармМенеджер
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, DownloadBrodcastReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarmManager.setRepeating(AlarmManager.RTC, intervalInMillis, AlarmManager.INTERVAL_HALF_DAY , pendingIntent);
        Log.d("ase21", "установлен аларм");
    }

    public static void CancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DownloadBrodcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.cancel(pendingIntent);
        Log.d("ase21", "снят аларм");
    }

    private boolean writeResponseBodyToDisk(Context context, ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(Environment
                    .getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS).getPath() +
                            File.separator +
                            SharePref.getInstance(context).getFileName());
            Toast.makeText(context, futureStudioIconFile.getPath(), Toast.LENGTH_SHORT).show();
            if (futureStudioIconFile.exists()){
                futureStudioIconFile.delete();
            }

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                    Log.d("ase21", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
