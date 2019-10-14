package com.asefactory.ase21.filereloader.presentation.presenter.mainsettings;

import android.content.Context;

import com.asefactory.ase21.filereloader.data.shared_prefs.SharePref;
import com.asefactory.ase21.filereloader.presentation.view.mainsettings.MainView;
import com.asefactory.ase21.filereloader.presentation.view.mainsettings.models.SavedInformationUIModel;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenterImpl extends MvpPresenter<MainView> implements MainPresenter {

    @Override
    public void getSavedInformation(Context context) {
        SavedInformationUIModel informationModel = new SavedInformationUIModel();
        informationModel.setDownloadURL(SharePref.getInstance(context).getDownloadUrl());
        informationModel.setFilename(SharePref.getInstance(context).getFileName());
        informationModel.setTimerValue(SharePref.getInstance(context).getTimeRepeating());
        informationModel.setSetTimer(SharePref.getInstance(context).isitWorks());
        getViewState().showSavedInformation(informationModel);
    }

    @Override
    public void saveSettings() {

    }

    @Override
    public void saveUrlIntoSharePrefs(Context context, String url) {
        SharePref.getInstance(context).saveDownloadUrl(url);
    }

    @Override
    public void saveFilenameIntoSharePrefs(Context context, String filename) {
        SharePref.getInstance(context).saveFileName(filename);
    }
}
