package com.celerapps.celermail.inboxGroup.inbox.models;


import android.content.Context;

import com.celerapps.celermail.inboxGroup.inbox.interfaces.IInboxInteractor;
import com.celerapps.celermail.inboxGroup.inbox.interfaces.IInboxVP;
import com.celerapps.celermail.inboxGroup.inbox.presenters.InboxPresenter;
import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.interfaces.IMailContainer;
import com.celerapps.databaseconnector.IDatabaseConnector;
import com.celerapps.databaseconnector.IMailService;

import java.util.List;

public class InboxInteractor implements IInboxInteractor {

    private IInboxVP.Presenter presenter;
    IDatabaseConnector databaseConnector;
    IMailService mailServiceMock;

    public InboxInteractor(InboxPresenter presenter, Context context) {
        this.presenter = presenter;
        this.databaseConnector = IDatabaseConnector.getInstance();
        this.mailServiceMock = IMailService.getInstance(context);
    }

    @Override
    public IMail getMail(String mailId) {
        return databaseConnector.getMail(mailId);
    }

    @Override
    public List<IMailContainer> getMailContainers(String folderId, boolean isUserCreated) {
        //List<IMailContainer> containers = databaseConnector.getMailContainers(folderId,isUserCreated);

        return mailServiceMock.getMailContainers(folderId);
    }

}
