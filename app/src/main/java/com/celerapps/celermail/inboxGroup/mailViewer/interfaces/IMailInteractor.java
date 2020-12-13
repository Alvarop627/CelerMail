package com.celerapps.celermail.inboxGroup.mailViewer.interfaces;

import com.celerapps.celermail.shared.interfaces.IMail;

public interface IMailInteractor {

    IMail getMail(String mailId);
}
