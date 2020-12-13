package com.celerapps.celermail.registerGroup.regForm.presenter;

import android.app.Activity;
import android.content.Context;

import com.celerapps.celermail.registerGroup.regForm.interfaces.IRegFormInteractor;
import com.celerapps.celermail.registerGroup.regForm.interfaces.IRegFormVP;
import com.celerapps.celermail.registerGroup.regForm.models.RegFormInteractor;
import com.celerapps.celermail.shared.interfaces.IMailAccount;

public class RegFormPresenter implements IRegFormVP.Presenter {

    private IRegFormInteractor interactor;
    private IRegFormVP.View view;

    public RegFormPresenter(IRegFormVP.View view) {
        this.interactor = new RegFormInteractor(this);
        this.view = view;
    }
    @Override
    public void start() {

    }

    public String getAccountId(){
        return this.interactor.getAccountId();
    }

    @Override
    public void createAccount(IMailAccount mAccount) {
        interactor.createAccount(mAccount);
    }
}
