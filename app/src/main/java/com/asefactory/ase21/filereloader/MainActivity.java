package com.asefactory.ase21.filereloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.asefactory.ase21.filereloader.shared_prefs.SharePref;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText downloadUrlEditText;
    EditText getTimeRepeatingEditText;
    Button saveUrlButton;
    Button setRepeatDownloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        downloadUrlEditText = findViewById(R.id.downloadUrlEditText);
        getTimeRepeatingEditText = findViewById(R.id.getTimeRepeatingEditText);
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
        saveUrlButton.setOnClickListener(this);
        setRepeatDownloadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveUrlButton:{
                saveUrlIntoSharePrefs(downloadUrlEditText.getText().toString());
                break;
            }
            case R.id.setRepeatDownloadButton:{
                setTimer(getTimeRepeatingEditText.getText().toString());
                break;
            }
            default:{
                break;
            }
        }
    }

    private void saveUrlIntoSharePrefs(String downloadUrl) {
        SharePref.getInstance(this).setDownloadUrl(downloadUrl);
    }

    private void setTimer(String timeRepeating) {
        SharePref.getInstance(this).setTimeRepeating(timeRepeating);
        //setTimer
    }

}
