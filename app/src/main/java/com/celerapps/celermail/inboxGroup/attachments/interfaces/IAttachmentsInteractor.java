package com.celerapps.celermail.inboxGroup.attachments.interfaces;

import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.interfaces.IMailAccount;

public interface IAttachmentsInteractor {
    IMailAccount getSelectedAccount();

    void createMail(IMail mMail);
}
