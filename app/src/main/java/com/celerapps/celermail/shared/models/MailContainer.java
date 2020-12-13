package com.celerapps.celermail.shared.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.interfaces.IMailContainer;

public class MailContainer implements IMailContainer, Parcelable {
    private String mailId, folderId, sender, subject, bodyText;
    private long dateTimeInMs;
    private Boolean isImportant, isViewed;

    public MailContainer(String mailId, String folderId, String sender, String subject, String bodyText, long dateTimeInMs, Boolean isImportant, Boolean isViewed) {
        this.mailId = mailId;
        this.folderId = folderId;
        this.sender = sender;
        this.subject = subject;
        this.bodyText = bodyText;
        this.dateTimeInMs = dateTimeInMs;
        this.isImportant = isImportant;
        this.isViewed = isViewed;
    }

    public MailContainer(IMail m) {
        this.mailId = m.getId();
        this.sender = m.getSenderName();
        this.subject = m.getSubject();
        if (m.getBodyText() != null) {
            this.bodyText = m.getBodyText().substring(0, Math.min(150, m.getBodyText().length())) + "...";
        }
        this.dateTimeInMs = m.getDateTimeInMs();
        this.isImportant = m.isImportant();
        this.isViewed = m.isViewed();
        this.folderId = "0";
    }

    protected MailContainer(Parcel in) {
        mailId = in.readString();
        folderId = in.readString();
        sender = in.readString();
        subject = in.readString();
        bodyText = in.readString();
        dateTimeInMs = in.readLong();
    }

    public static final Creator<MailContainer> CREATOR = new Creator<MailContainer>() {
        @Override
        public MailContainer createFromParcel(Parcel in) {
            return new MailContainer(in);
        }

        @Override
        public MailContainer[] newArray(int size) {
            return new MailContainer[size];
        }
    };

    @Override
    public String getMailId() {
        return mailId;
    }

    @Override
    public String getFolderId() {
        return mailId;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public boolean isImportant() {
        return isImportant;
    }

    @Override
    public boolean isViewed() {
        return isViewed;
    }

    @Override
    public String getBodyText() {
        return bodyText;
    }

    @Override
    public long getDateTimeInMs() {
        return dateTimeInMs;
    }

    @Override
    public int compareTo(IMailContainer m) {
        int estado = -1;
        if (this.dateTimeInMs == m.getDateTimeInMs()) {
            //Los objetos son iguales
            estado = 0;
        } else if (this.dateTimeInMs < m.getDateTimeInMs()) {
            //El objeto 1 es mayor que la pasada por parametro
            estado = 1;
        }
        return estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mailId);
        dest.writeString(sender);
        dest.writeString(subject);
        dest.writeString(bodyText);
        dest.writeLong(dateTimeInMs);
    }

    @Override
    public String toString() {
        return "MailContainer{" +
                "id='" + mailId + '\'' +
                ", sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                ", bodyText='" + bodyText + '\'' +
                ", dateTimeInMs=" + dateTimeInMs +
                '}';
    }
}
