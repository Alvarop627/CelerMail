package com.celerapps.celermail.inboxGroup.mailComposer.models;


import com.celerapps.celermail.inboxGroup.mailComposer.interfaces.IMailComposerInteractor;
import com.celerapps.celermail.inboxGroup.mailComposer.interfaces.IMailComposerPresenter;

public class MailComposerInteractor implements IMailComposerInteractor {

    private IMailComposerPresenter presenter;

    public MailComposerInteractor(IMailComposerPresenter presenter) {
        this.presenter = presenter;
    }
}
