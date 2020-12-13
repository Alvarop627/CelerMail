package com.celerapps.celermail.shared.models;

import android.graphics.Bitmap;

import com.celerapps.celermail.shared.interfaces.IMailAccountHeader;

public class MailAccountHeader implements IMailAccountHeader {

    private String accountId;
    private Bitmap image;

    public MailAccountHeader(String accountId, Bitmap image) {
        this.accountId = accountId;
        this.image = image;
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public Bitmap getImage() {
        return image;
    }
}
