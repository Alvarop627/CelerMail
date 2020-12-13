package com.celerapps.celermail.inboxGroup.inbox.interfaces;


import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.interfaces.IMailContainer;

import java.util.List;

public interface IInboxInteractor {
    IMail getMail(String mailId);
    List<IMailContainer> getMailContainers(String folderId, boolean isUserCreated);

}

