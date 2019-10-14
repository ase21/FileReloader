package com.asefactory.ase21.filereloader.presentation.view.mainsettings;

import com.asefactory.ase21.filereloader.presentation.view.mainsettings.models.SavedInformationUIModel;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void showSavedInformation(SavedInformationUIModel informationModel);
}
