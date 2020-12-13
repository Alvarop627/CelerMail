package com.celerapps.celermail.home.models;


import android.app.Activity;
import android.content.Context;

import com.celerapps.celermail.home.interfaces.IHomeInteractor;
import com.celerapps.celermail.home.interfaces.IHomeVP;
import com.celerapps.databaseconnector.IDatabaseConnector;

public class HomeInteractor implements IHomeInteractor {

    private IHomeVP.Presenter presenter;
    IDatabaseConnector databaseConnector;

    public HomeInteractor(IHomeVP.Presenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public void signIn(String email, String password) {
        this.databaseConnector = IDatabaseConnector.getInstance();
        databaseConnector.signIn(email, password);
    }
}
