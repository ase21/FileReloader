package com.asefactory.ase21.filereloader.domain.mainsettings;

import io.reactivex.Single;

public interface MainSettingsRepository {

    void saveDownloadURL(String url);

    void saveFileName(String fileName);

    void saveTimeRepeating(String timeRepeating);

    void setTimer(boolean timerFlag);

    Single<String> getSavedDownloadURL();

    Single<String> getFileName();

    Single<String> getSavedTimeRepeating();

    Single<Boolean> isTimerWorks();
}
