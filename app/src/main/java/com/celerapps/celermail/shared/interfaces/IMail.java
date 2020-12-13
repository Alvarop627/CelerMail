package com.celerapps.celermail.shared.interfaces;

import android.os.Parcelable;

import java.util.List;

public interface IMail extends Parcelable, Comparable<IMail> {

    String getId();

    String getSenderName();

    void setSenderName(String name);

    String getBodyText();

    void setBodyText(String bodyText);

    long getDateTimeInMs();

    String getSubject();

    void setSubject(String subject);

    boolean isImportant();

    boolean isViewed();

    void setSenderAddress(String senderAddress);

    void setDateTimeInMs(long dateTimeInMs);

    void setImportant(Boolean important);

    void setAttachments(List<IAttachment> attachments);

    void setId();

    void setFolderId(String s);
}
