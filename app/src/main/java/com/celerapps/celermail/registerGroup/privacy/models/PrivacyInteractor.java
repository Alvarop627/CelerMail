package com.celerapps.celermail.registerGroup.privacy.models;

import com.celerapps.celermail.registerGroup.privacy.interfaces.IPrivacyInteractor;
import com.celerapps.celermail.registerGroup.privacy.interfaces.IPrivacyVP;

public class PrivacyInteractor implements IPrivacyInteractor {

    private IPrivacyVP.Presenter presenter;

    public PrivacyInteractor(IPrivacyVP.Presenter presenter) {
        this.presenter = presenter;
    }
}
