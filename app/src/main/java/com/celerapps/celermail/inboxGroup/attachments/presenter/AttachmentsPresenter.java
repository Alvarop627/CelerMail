package com.celerapps.celermail.inboxGroup.attachments.presenter;


import com.celerapps.celermail.inboxGroup.attachments.interfaces.IAttachmentsInteractor;
import com.celerapps.celermail.inboxGroup.attachments.interfaces.IAttachmentsPresenter;
import com.celerapps.celermail.inboxGroup.attachments.models.AttachmentsInteractor;
import com.celerapps.celermail.inboxGroup.attachments.views.AttachmentsActivity;
import com.celerapps.celermail.shared.interfaces.IMail;

public class AttachmentsPresenter implements IAttachmentsPresenter {

    private IAttachmentsInteractor interactor;
    private AttachmentsActivity view;

    public AttachmentsPresenter(AttachmentsActivity view) {
        this.interactor = new AttachmentsInteractor(this);
        this.view = view;
    }
    @Override
    public void start() {

    }

    public String getSelectedAccountName() {
        return interactor.getSelectedAccount().getName();
    }

    public String getSelectedAccountEmail() {
        return interactor.getSelectedAccount().getEmailAddress();
    }

    public String getSelectedAccountId() {
        return interactor.getSelectedAccount().getAccountId();
    }

    @Override
    public void createMail(IMail mMail) {
        interactor.createMail(mMail);
    }
}
