package com.celerapps.celermail.home.presenter;

import android.app.Activity;
import android.content.Context;

import com.celerapps.celermail.home.interfaces.IHomeInteractor;
import com.celerapps.celermail.home.interfaces.IHomeVP;
import com.celerapps.celermail.home.models.HomeInteractor;
import com.celerapps.celermail.inboxGroup.mailComposer.interfaces.IMailComposerInteractor;
import com.celerapps.celermail.inboxGroup.mailComposer.interfaces.IMailComposerPresenter;
import com.celerapps.celermail.inboxGroup.mailComposer.interfaces.IMailComposerView;

public class HomePresenter implements IHomeVP.Presenter {

    private IHomeInteractor interactor;
    private IHomeVP.View view;

    public HomePresenter(IHomeVP.View view) {
        this.interactor = new HomeInteractor(this);
        this.view = view;
    }
    @Override
    public void start() {

    }

    public void signIn(String email, String password){
        interactor.signIn(email, password);
    }
}
