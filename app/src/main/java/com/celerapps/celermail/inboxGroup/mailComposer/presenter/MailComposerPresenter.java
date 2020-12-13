package com.celerapps.celermail.inboxGroup.mailComposer.presenter;

import com.celerapps.celermail.inboxGroup.mailComposer.interfaces.IMailComposerInteractor;
import com.celerapps.celermail.inboxGroup.mailComposer.interfaces.IMailComposerPresenter;
import com.celerapps.celermail.inboxGroup.mailComposer.interfaces.IMailComposerView;
import com.celerapps.celermail.inboxGroup.mailComposer.models.MailComposerInteractor;

public class MailComposerPresenter implements IMailComposerPresenter {

    private IMailComposerInteractor interactor;
    private IMailComposerView view;

    public MailComposerPresenter(IMailComposerView view) {
        this.interactor = new MailComposerInteractor(this);
        this.view = view;
    }
    @Override
    public void start() {

    }
}
