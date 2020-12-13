package com.celerapps.celermail.registerGroup.terms.presenter;


import com.celerapps.celermail.registerGroup.terms.interfaces.ITermsInteractor;
import com.celerapps.celermail.registerGroup.terms.interfaces.ITermsVP;
import com.celerapps.celermail.registerGroup.terms.models.TermsInteractor;

public class TermsPresenter implements ITermsVP.Presenter {

    private ITermsInteractor interactor;
    private ITermsVP.View view;

    public TermsPresenter(ITermsVP.View view) {
        this.interactor = new TermsInteractor(this);
        this.view = view;
    }
    @Override
    public void start() {

    }
}
