package com.celerapps.celermail.inboxGroup.mailViewer.models;


import android.content.Context;

import com.celerapps.celermail.inboxGroup.mailViewer.interfaces.IMailInteractor;
import com.celerapps.celermail.inboxGroup.mailViewer.interfaces.IMailVP;
import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.databaseconnector.DatabaseConnector;
import com.celerapps.databaseconnector.IDatabaseConnector;
import com.celerapps.databaseconnector.IMailService;

public class MailInteractor implements IMailInteractor {

    private IMailVP.Presenter presenter;
    IDatabaseConnector databaseConnector;
    IMailService mailServiceMock;

    public MailInteractor(IMailVP.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.databaseConnector = new DatabaseConnector(context);
        this.mailServiceMock = IMailService.getInstance(context);
    }

    @Override
    public IMail getMail(String mailId) {
        return mailServiceMock.getMail(mailId);
        //return databaseConnector.getMail(mailId);
    }
}
