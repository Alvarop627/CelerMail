package com.celerapps.celermail.registerGroup.regForm.interfaces;

import android.app.Activity;
import android.content.Context;

import com.celerapps.celermail.shared.interfaces.IMailAccount;

public interface IRegFormInteractor {
    String getAccountId();
    void createAccount(IMailAccount mAccount);
}
