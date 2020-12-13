package com.celerapps.databaseconnector;

import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.interfaces.IMailAccount;
import com.celerapps.celermail.shared.interfaces.IMailAccountHeader;
import com.celerapps.celermail.shared.interfaces.IMailContainer;

import java.util.List;

public interface IDatabaseConnector {
    IMail getMail(String mailId);
    List<IMailContainer> getMailContainers(String folderId, boolean isUserCreated);
    void createAccountFolder(String folderName);

    static DatabaseConnector getInstance() {
        return DatabaseConnector.getInstance();
    }

    IMailAccount getSelectedMailAccount();
    String getUserId();
    void createAccount(IMailAccount mAccount);
    void signIn(String email, String password);

    IMailAccountHeader getMailAccountHeader();

    void createMail(IMail mMail);

    void signOut();
}
