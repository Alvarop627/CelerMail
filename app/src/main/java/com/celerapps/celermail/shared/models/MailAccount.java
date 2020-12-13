package com.celerapps.celermail.shared.models;

import android.graphics.Bitmap;

import com.celerapps.celermail.shared.interfaces.IMailAccount;
import com.celerapps.celermail.shared.interfaces.IMailAccountHeader;
import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;

import java.util.List;

public class MailAccount implements IMailAccount {

    private String accountId, name, emailAddress, password, gender, countrie;
    private Bitmap image;
    private List<IMailFolderInfo> defaultFolders;
    private List<IMailFolderInfo> customFolders;
    private String folderId;
    private long birthdate;
    private String phone;

    public MailAccount() {

    }

    public MailAccount(String accountId, String name, String emailAddress, String password, String gender, String countrie, Bitmap image, List<IMailFolderInfo> defaultFolders, List<IMailFolderInfo> customFolders, String folderId, long birthdate, String phone) {
        this.accountId = accountId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.gender = gender;
        this.countrie = countrie;
        this.image = image;
        this.defaultFolders = defaultFolders;
        this.customFolders = customFolders;
        this.folderId = folderId;
        this.birthdate = birthdate;
        this.phone = phone;
    }

    public String getAccountId() {
        return accountId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getGender() {
        return gender;
    }

    public String getCountrie() {
        return countrie;
    }

    public long getBirthdate() {
        return birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setDefaultFolders(List<IMailFolderInfo> defaultFolders) {
        this.defaultFolders = defaultFolders;
    }

    public void setCustomFolders(List<IMailFolderInfo> customFolders) {
        this.customFolders = customFolders;
    }

    @Override
    public List<IMailFolderInfo> getDefaultFolders() {
        return defaultFolders;
    }

    public List<IMailFolderInfo> getCustomFolders() {
        return customFolders;
    }

    @Override
    public String getFolderId() {
        return folderId;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public IMailAccountHeader getMailAccountHeader() {
        IMailAccountHeader mailAccountHeader = null;
        if(this.getImage()!=null){
             mailAccountHeader = new MailAccountHeader(this.getAccountId(), this.getImage());
        }
        return mailAccountHeader;
    }


}
