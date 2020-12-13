package com.celerapps.celermail.inboxGroup.inboxMenu.interfaces;

import com.celerapps.celermail.shared.interfaces.IAttachment;
import com.celerapps.celermail.shared.interfaces.IMailAccount;
import com.celerapps.celermail.shared.interfaces.IMailAccountHeader;
import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;

import java.util.List;

public interface IInboxMenuInteractor {
    IMailAccountHeader getMailAccountHeader();
    List<IMailFolderInfo> getMailFolderInfo(boolean isUserCreated);

    IMailAccount getSelectedAccount();

    void createAccountFolder(String folderName);

    void signOut();
}
