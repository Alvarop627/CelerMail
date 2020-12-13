package com.celerapps.celermail.shared.interfaces;

import android.graphics.Bitmap;

import java.util.List;

public interface IMailAccount {

    String getAccountId();

    String getName();

    String getEmailAddress();

    String getPassword();

    String getGender();

    String getCountrie();

    Bitmap getImage();

    List<IMailFolderInfo> getDefaultFolders();

    List<IMailFolderInfo> getCustomFolders();

    String getFolderId();

    long getBirthdate();

    String getPhone();

    IMailAccountHeader getMailAccountHeader();

    void setImage(Bitmap image);

    void setDefaultFolders(List<IMailFolderInfo> defaultFolders);

    void setCustomFolders(List<IMailFolderInfo> customFolders);
}
