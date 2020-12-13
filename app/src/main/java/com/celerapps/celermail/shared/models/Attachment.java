package com.celerapps.celermail.shared.models;


import com.celerapps.celermail.shared.interfaces.IAttachment;

import java.util.Random;

public class Attachment implements IAttachment {

    private String mailId, name, type;
    private long size;

    public Attachment(String mailId, String name, String type, long size) {
        this.mailId = mailId;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public Attachment(){
        Random r = new Random();
        this.name = "Archivo"+ r.nextInt();
    }

    public String getMailId() {
        return mailId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "mailId='" + mailId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                '}';
    }
}
