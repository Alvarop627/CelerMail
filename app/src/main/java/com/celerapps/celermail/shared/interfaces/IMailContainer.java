package com.celerapps.celermail.shared.interfaces;


import android.os.Parcelable;

public interface IMailContainer extends Parcelable, Comparable<IMailContainer> {

    String getMailId();

    String getFolderId();

    String getSender();

    String getBodyText();

    long getDateTimeInMs();

    String getSubject();

    boolean isImportant();

    boolean isViewed();
}
