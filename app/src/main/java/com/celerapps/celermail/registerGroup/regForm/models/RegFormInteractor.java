package com.celerapps.celermail.registerGroup.regForm.models;


import android.app.Activity;
import android.content.Context;

import com.celerapps.celermail.registerGroup.regForm.interfaces.IRegFormInteractor;
import com.celerapps.celermail.registerGroup.regForm.presenter.RegFormPresenter;
import com.celerapps.celermail.shared.interfaces.IMailAccount;
import com.celerapps.databaseconnector.IDatabaseConnector;

public class RegFormInteractor implements IRegFormInteractor {

    private RegFormPresenter presenter;
    private IDatabaseConnector databaseConnector;

    public RegFormInteractor(RegFormPresenter presenter) {
        this.presenter = presenter;
        this.databaseConnector = IDatabaseConnector.getInstance();
    }

    @Override
    public String getAccountId() {
        return databaseConnector.getUserId();
    }

    @Override
    public void createAccount(IMailAccount mAccount) {
        databaseConnector.createAccount(mAccount);
    }
}
