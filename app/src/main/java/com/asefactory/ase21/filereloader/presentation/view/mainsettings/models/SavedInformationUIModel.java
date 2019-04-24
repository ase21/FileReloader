package com.asefactory.ase21.filereloader.presentation.view.mainsettings.models;

public class SavedInformationUIModel {
    private String filename;

    private String downloadURL;

    private String timerValue;

    private  boolean isSetTimer;

    public String getFileName() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getTimerValue() {
        return timerValue;
    }

    public void setTimerValue(String timerValue) {
        this.timerValue = timerValue;
    }

    public boolean isSetTimer() {
        return isSetTimer;
    }

    public void setSetTimer(boolean setTimer) {
        isSetTimer = setTimer;
    }
}
