package com.asefactory.ase21.filereloader.presentation.view.mainsettings;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;

import com.asefactory.ase21.filereloader.R;
import com.asefactory.ase21.filereloader.data.shared_prefs.SharePref;
import com.asefactory.ase21.filereloader.di.App;
import com.asefactory.ase21.filereloader.presentation.presenter.mainsettings.MainPresenterImpl;
import com.asefactory.ase21.filereloader.presentation.view.downloadreceiver.DownloadBrodcastReceiver;
import com.asefactory.ase21.filereloader.presentation.view.mainsettings.models.SavedInformationUIModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    public static final int REQUEST_PERMISSIONS_CODE = 101;

    @BindView(R.id.downloadUrlEditText)
    EditText downloadUrlEditText;
    @BindView(R.id.getTimeRepeatingEditText)
    EditText getTimeRepeatingEditText;
    @BindView(R.id.filenameEditText)
    EditText filenameEditText;
    @BindView(R.id.saveUrlButton)
    Button saveUrlButton;
    @BindView(R.id.setRepeatDownloadButton)
    Button setRepeatDownloadButton;

    @InjectPresenter
    MainPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        presenter.getSavedInformation(this);
    }

    private void setButtonText(boolean isSet) {
        if (isSet) {
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

    @Override
    public void showSavedInformation(SavedInformationUIModel informationModel) {
        if (!informationModel.getDownloadURL().equals("")) {
            downloadUrlEditText.setText(informationModel.getDownloadURL());
        }
        if (!informationModel.getFileName().equals("")) {
            filenameEditText.setText(informationModel.getFileName());
        }
        if (!informationModel.getTimerValue().equals("")) {
            getTimeRepeatingEditText.setText(informationModel.getTimerValue());
        }
        setButtonText(informationModel.isSetTimer());
    }

    @OnClick(R.id.saveUrlButton)
    public void onSaveUrlButtonClicked() {
        presenter.saveUrlIntoSharePrefs(this, downloadUrlEditText.getText().toString());
        presenter.saveFilenameIntoSharePrefs(this,filenameEditText.getText().toString());
    }

    @OnClick(R.id.setRepeatDownloadButton)
    public void onSetRepeatDownloadButtonClicked() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)||(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))  {
            //Request permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_CODE);
            return;
        }
        if (!SharePref.getInstance(this).isitWorks()) {
            setTimer(getTimeRepeatingEditText.getText().toString());
        } else {
            cancelTimer();
        }
        setButtonText(SharePref.getInstance(this).isitWorks());
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
}