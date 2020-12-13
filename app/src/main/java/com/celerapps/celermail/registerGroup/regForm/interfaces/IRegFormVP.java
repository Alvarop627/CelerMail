package com.celerapps.celermail.registerGroup.regForm.interfaces;

import android.app.Activity;
import android.content.Context;

import com.celerapps.celermail.BasePresenter;
import com.celerapps.celermail.BaseView;
import com.celerapps.celermail.shared.interfaces.IMailAccount;

public interface IRegFormVP {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        String getAccountId();

        void createAccount(IMailAccount mAccount);
    }
}
