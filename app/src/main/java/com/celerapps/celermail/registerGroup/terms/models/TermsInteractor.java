package com.celerapps.celermail.registerGroup.terms.models;


import com.celerapps.celermail.registerGroup.terms.interfaces.ITermsVP;
import com.celerapps.celermail.registerGroup.terms.interfaces.ITermsInteractor;

public class TermsInteractor implements ITermsInteractor {

    private ITermsVP.Presenter presenter;

    public TermsInteractor(ITermsVP.Presenter presenter) {
        this.presenter = presenter;
    }
}
