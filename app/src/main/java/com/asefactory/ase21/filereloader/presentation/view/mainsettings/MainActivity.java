package com.asefactory.ase21.filereloader.presentation.view.mainsettings;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.asefactory.ase21.filereloader.R;
import com.asefactory.ase21.filereloader.di.App;
import com.asefactory.ase21.filereloader.presentation.view.downloadreceiver.DownloadBrodcastReceiver;
import com.asefactory.ase21.filereloader.data.shared_prefs.SharePref;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText downloadUrlEditText;
    EditText getTimeRepeatingEditText;
    EditText filenameEditText;
    Button saveUrlButton;
    Button setRepeatDownloadButton;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        App.getComponent().inject(this);
    }

    private void initUI() {
        downloadUrlEditText = findViewById(R.id.downloadUrlEditText);
        getTimeRepeatingEditText = findViewById(R.id.getTimeRepeatingEditText);
        filenameEditText = findViewById(R.id.filenameEditText);
        saveUrlButton = findViewById(R.id.saveUrlButton);
        setRepeatDownloadButton = findViewById(R.id.setRepeatDownloadButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initUX();
    }

    private void initUX() {
        if (!SharePref.getInstance(this).getDownloadUrl().equals("")){
            downloadUrlEditText.setText(SharePref.getInstance(this).getDownloadUrl());
        }
        if (!SharePref.getInstance(this).getFileName().equals("")){
            filenameEditText.setText(SharePref.getInstance(this).getFileName());
        }
        if (!SharePref.getInstance(this).getTimeRepeating().equals("")){
            getTimeRepeatingEditText.setText(SharePref.getInstance(this).getTimeRepeating());
        }
        setButtonText();
        saveUrlButton.setOnClickListener(this);
        setRepeatDownloadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveUrlButton:{
                saveUrlIntoSharePrefs(downloadUrlEditText.getText().toString());
                saveFilenameIntoSharePrefs(filenameEditText.getText().toString());
                break;
            }
            case R.id.setRepeatDownloadButton:{
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
                    return;
                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                    return;
                }
                if (!SharePref.getInstance(this).isitWorks()){
                    setTimer(getTimeRepeatingEditText.getText().toString());
                } else {
                    cancelTimer();
                }
                setButtonText();
                break;
            }
            default:{
                break;
            }
        }
    }

    private void saveUrlIntoSharePrefs(String downloadUrl) {
        SharePref.getInstance(this).saveDownloadUrl(downloadUrl);
    }

    private void saveFilenameIntoSharePrefs(String filename) {
        SharePref.getInstance(this).saveFileName(filename);
    }

    private void setTimer(String timeRepeating) {
        SharePref.getInstance(this).saveTimeRepeating(timeRepeating);
        SharePref.getInstance(this).setTimer(true);
        setAlarmManager();
    }

    private void cancelTimer() {
        SharePref.getInstance(this).setTimer(false);
        cancelAlarmManager();
    }

    private void setButtonText() {
        if (SharePref.getInstance(this).isitWorks()){
            setRepeatDownloadButton.setText("Cancel Timer");
        } else {
            setRepeatDownloadButton.setText("Set Timer");
        }
    }

    private void setAlarmManager() {
        DownloadBrodcastReceiver.SetAlarm(this, Integer.parseInt(getTimeRepeatingEditText.getText().toString()));
    }

    private void cancelAlarmManager() {
        DownloadBrodcastReceiver.CancelAlarm(this);
    }

}
