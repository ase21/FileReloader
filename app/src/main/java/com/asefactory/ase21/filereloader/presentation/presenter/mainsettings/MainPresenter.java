package com.asefactory.ase21.filereloader.presentation.presenter.mainsettings;

import android.content.Context;

public interface MainPresenter {

    void getSavedInformation(Context context);

    void saveSettings();

    void saveUrlIntoSharePrefs(Context context, String url);

    void saveFilenameIntoSharePrefs(Context context, String filename);
}
