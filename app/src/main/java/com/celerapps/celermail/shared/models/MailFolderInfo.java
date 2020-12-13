package com.celerapps.celermail.shared.models;

import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;

public class MailFolderInfo implements IMailFolderInfo {

    private String folderId, folderName;
    private boolean isUserCreated;

    public MailFolderInfo(String folderId, String folderName, boolean isUserCreated) {
        this.folderId = folderId;
        this.folderName = folderName;
        this.isUserCreated = isUserCreated;
    }

    public MailFolderInfo(String folderName) {
        this.folderId = folderId;
        this.folderName = folderName;
        this.isUserCreated = true;
    }

    public MailFolderInfo() {
    }

    @Override
    public String getFolderId() {
        return folderId;
    }

    @Override
    public String getFolderName() {
        return folderName;
    }

    @Override
    public boolean isUserCreated() {
        return isUserCreated;
    }
}
