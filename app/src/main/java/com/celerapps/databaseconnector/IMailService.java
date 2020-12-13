package com.celerapps.databaseconnector;

import android.content.Context;

import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.interfaces.IMailAccount;
import com.celerapps.celermail.shared.interfaces.IMailAccountHeader;
import com.celerapps.celermail.shared.interfaces.IMailContainer;

import java.util.List;

public interface IMailService {
    IMail getMail(String mailId);
    List<IMailContainer> getMailContainers(String folderId);

    static MailServiceMock getInstance(Context context) {
        return MailServiceMock.getInstance(context);
    }

    void createMail(IMail mMail);
}
