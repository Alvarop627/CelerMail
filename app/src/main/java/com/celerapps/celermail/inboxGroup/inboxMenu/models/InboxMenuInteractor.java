package com.celerapps.celermail.inboxGroup.inboxMenu.models;


import android.content.Context;

import com.celerapps.celermail.inboxGroup.inboxMenu.interfaces.IInboxMenuInteractor;
import com.celerapps.celermail.inboxGroup.inboxMenu.interfaces.IInboxMenuVP;
import com.celerapps.celermail.inboxGroup.inboxMenu.presenter.InboxMenuPresenter;
import com.celerapps.celermail.shared.interfaces.IMailAccount;
import com.celerapps.celermail.shared.interfaces.IMailAccountHeader;
import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;
import com.celerapps.databaseconnector.IDatabaseConnector;

import java.util.ArrayList;
import java.util.List;

public class InboxMenuInteractor implements IInboxMenuInteractor {

    private IInboxMenuVP.Presenter presenter;
    private IDatabaseConnector databaseConnector;
    private IMailAccount myAccount;
    private String myAccountId;
    private List<IMailFolderInfo> mailFolderInfos = new ArrayList<IMailFolderInfo>();

    public InboxMenuInteractor(InboxMenuPresenter presenter, Context context) {
        this.presenter = presenter;
        this.databaseConnector = IDatabaseConnector.getInstance();
        if(databaseConnector!=null){
            myAccountId = databaseConnector.getUserId();
            myAccount = databaseConnector.getSelectedMailAccount();
        }
    }

    public IMailAccountHeader getMailAccountHeader() {
        return databaseConnector.getMailAccountHeader();
    }

    public List<IMailFolderInfo> getMailFolderInfo(boolean isUserCreated) {

        if (myAccount != null) {
            if(isUserCreated==false) {
                mailFolderInfos = myAccount.getDefaultFolders();
            }else{
                mailFolderInfos = myAccount.getCustomFolders();
            }
        }
        return mailFolderInfos;
    }

    @Override
    public IMailAccount getSelectedAccount() {
        if (databaseConnector != null) {
            myAccount = databaseConnector.getSelectedMailAccount();

        }
        return myAccount;
    }

    @Override
    public void createAccountFolder(String folderName) {
        databaseConnector.createAccountFolder(folderName);
    }

    @Override
    public void signOut() {
        databaseConnector.signOut();
    }

}
