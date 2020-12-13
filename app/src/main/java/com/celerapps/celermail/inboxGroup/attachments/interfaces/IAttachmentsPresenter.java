package com.celerapps.celermail.inboxGroup.attachments.interfaces;


import com.celerapps.celermail.BasePresenter;
import com.celerapps.celermail.shared.interfaces.IMail;

public interface IAttachmentsPresenter extends BasePresenter {
    String getSelectedAccountName();
    String getSelectedAccountEmail();
    String getSelectedAccountId();
    void createMail(IMail mUncertifiedMail);
}
