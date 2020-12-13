package com.celerapps.celermail.registerGroup.privacy.presenter;


import com.celerapps.celermail.registerGroup.privacy.interfaces.IPrivacyInteractor;
import com.celerapps.celermail.registerGroup.privacy.interfaces.IPrivacyVP;
import com.celerapps.celermail.registerGroup.privacy.models.PrivacyInteractor;

public class PrivacyPresenter implements IPrivacyVP.Presenter {

    private IPrivacyInteractor interactor;
    private IPrivacyVP.View view;

    public PrivacyPresenter(IPrivacyVP.View view) {
        this.interactor = new PrivacyInteractor(this);
        this.view = view;
    }
    @Override
    public void start() {

    }
}
