package com.celerapps.celermail.inboxGroup.inbox.presenters;


import android.content.Context;

import com.celerapps.celermail.inboxGroup.inbox.interfaces.IInboxInteractor;
import com.celerapps.celermail.inboxGroup.inbox.interfaces.IInboxVP;
import com.celerapps.celermail.inboxGroup.inbox.models.InboxInteractor;
import com.celerapps.celermail.shared.interfaces.IMailContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InboxPresenter implements IInboxVP.Presenter {

    private IInboxInteractor interactor;
    private IInboxVP.View view;

    public InboxPresenter(IInboxVP.View view, Context context) {
        this.interactor = new InboxInteractor(this, context);
        this.view = view;
    }
    @Override
    public void start() {

    }

    @Override
    public List<IMailContainer> getMailContainers(String folderId, boolean isUserCreated, boolean isImportant) {
        List<IMailContainer> containers =  interactor.getMailContainers(folderId,isUserCreated);
        List<IMailContainer> mailContainers = new ArrayList<>();
        for (IMailContainer mc: containers) {
            if (mc.isImportant()==isImportant){
                mailContainers.add(mc);
            }
        }
        return mailContainers;
    }


}
