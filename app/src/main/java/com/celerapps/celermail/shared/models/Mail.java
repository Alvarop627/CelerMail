package com.celerapps.celermail.shared.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.celerapps.celermail.shared.interfaces.IAttachment;
import com.celerapps.celermail.shared.interfaces.IMail;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Mail implements IMail, Parcelable {

    private String mailId;
    private String senderName, senderAddress, receiverAddress, cc, cco, subject, bodyText;
    private long dateTimeInMs;
    private Boolean isImportant, isViewed;
    private List<IAttachment> attachments;
    private static final String ORIGINAL = "ÁáÉéÍíÓóÚúÑñÜü";
    private static final String REPLACEMENT = "AaEeIiOoUuNnUu";
    private String folderId;


    public Mail(String senderName, String senderAddress, String receiverAddress, String cc, String cco, String subject, String bodyText, long dateTimeInMs, Boolean isImportant, List<IAttachment> attachments) {
        this.mailId = java.util.UUID.randomUUID().toString();
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.receiverAddress = receiverAddress;
        this.cc = cc;
        this.cco = cco;
        this.subject = subject;
        this.bodyText = bodyText;
        this.dateTimeInMs = dateTimeInMs;
        this.isImportant = isImportant;
        this.isViewed = false;
        this.attachments = attachments;
    }

    public Mail(String id, String senderName, String senderAddress, String receiverAddress, String cc, String cco, String subject, String bodyText, long dateTimeInMs, Boolean isImportant, List<IAttachment> attachments) {
        this.mailId = id;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.receiverAddress = receiverAddress;
        this.cc = cc;
        this.cco = cco;
        this.subject = subject;
        this.bodyText = bodyText;
        this.dateTimeInMs = dateTimeInMs;
        this.isImportant = isImportant;
        this.isViewed = false;
        this.attachments = attachments;
    }

    public Mail() {}


    public Mail(Random r) {
        //mock
        mailId = java.util.UUID.randomUUID().toString();
        this.folderId = String.valueOf(r.nextInt(7));
        String[] names = {"Antonio", "Jose", "María", "Ana", "Manuel", "Francisco", "Carmen", "Isabel", "Juan", "Javier", "Daniel", "Sara", "Paula", "Elena", "Pilar", "Luis", "Alberto", "Jorge", "Sergio", "Laura", "Cristina", "Raquel", "Raúl", "Álvaro", "Adrián"};
        String[] surnames = {"García", "González", "Rodríguez", "Fernández", "López", "Martínez", "Sánchez", "Pérez", "Gómez", "Martin", "Jiménez", "Ruiz", "Hernández", "Diaz", "Moreno", "Muñoz", "Álvarez", "Romero", "Alonso", "Gutiérrez", "Navarro", "Torres", "Domínguez", "Vázquez", "Ramos", "Gil"};
        String name = names[r.nextInt(names.length)];
        String surname1 = surnames[r.nextInt(surnames.length)];
        String surname2 = surnames[r.nextInt(surnames.length)];
        this.senderName = name + " " + surname1 + " " + surname2;
        String email = (name.substring(0, 3) + surname1.substring(0, 3) + surname2.substring(0, 3) + "@onedomain.com").toLowerCase();
        this.senderAddress = stripAccents(email);
        //SimpleDateFormat formatterTime = new SimpleDateFormat(" HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, r.nextInt(30));
        cal.set(Calendar.MONTH, r.nextInt(12) - 1);
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.HOUR_OF_DAY, r.nextInt(24));
        cal.set(Calendar.MINUTE, r.nextInt(60));
        this.dateTimeInMs = cal.getTimeInMillis();
        this.subject = "Asunto del mensaje";
        this.isImportant = r.nextBoolean();
        this.isViewed = r.nextBoolean();
        String[] texts = new String[4];
        texts[0] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sed elit lacus. Maecenas imperdiet eleifend felis et faucibus. Etiam libero est, pulvinar eget aliquet eget, mollis eu libero. In maximus nisl at elit tincidunt euismod. Duis luctus urna vitae purus mollis elementum. Praesent consectetur dui id dui pretium viverra. Aenean sit amet sollicitudin nulla.\n" +
                "\n" +
                "Sed tempus sit amet nibh eu porta. Nullam ac quam sed ex sodales iaculis. Pellentesque vel pulvinar risus. Praesent maximus nisi nunc, a aliquet neque ullamcorper ut. Nam leo neque, elementum non dolor eget, tincidunt mattis purus. Fusce et ex ut nibh pretium ornare ac eu tortor. Nam at leo aliquam, laoreet neque euismod, volutpat est. Proin nec neque congue, pulvinar eros luctus, tincidunt ligula. Duis accumsan dolor convallis enim suscipit commodo.";
        texts[1] = "Una mañana, tras un sueño intranquilo, Gregorio Samsa se despertó convertido en un monstruoso insecto. Estaba echado de espaldas sobre un duro caparazón y, al alzar la cabeza, vio su vientre convexo y oscuro, surcado por curvadas callosidades, sobre el que casi no se aguantaba la colcha, que estaba a punto de escurrirse hasta el suelo. Numerosas patas, penosamente delgadas en comparación con el grosor normal de sus piernas, se agitaban sin concierto. - ¿Qué me ha ocurrido? No estaba soñando. Su habitación, una habitación normal, aunque muy pequeña, tenía el aspecto habitual. Sobre la mesa había desparramado un muestrario";
        texts[2] = "Y, viéndole don Quijote de aquella manera, con muestras de tanta tristeza, le dijo: Sábete, Sancho, que no es un hombre más que otro si no hace más que otro. Todas estas borrascas que nos suceden son señales de que presto ha de serenar el tiempo y han de sucedernos bien las cosas; porque no es posible que el mal ni el bien sean durables, y de aquí se sigue que, habiendo durado mucho el mal, el bien está ya cerca. Así que, no debes congojarte por las desgracias que a mí me suceden, pues a ti no te cabe";
        texts[3] = "Quiere la boca exhausta vid, kiwi, piña y fugaz jamón. Fabio me exige, sin tapujos, que añada cerveza al whisky. Jovencillo emponzoñado de whisky, ¡qué figurota exhibes! La cigüeña tocaba cada vez mejor el saxofón y el búho pedía kiwi y queso. El jefe buscó el éxtasis en un imprevisto baño de whisky y gozó como un duque. Exhíbanse politiquillos zafios, con orejas kilométricas y uñas de gavilán. El cadáver de Wamba, rey godo de España, fue exhumado y trasladado en una caja de zinc que pesó un kilo. El pingüino Wenceslao hizo kilómetros bajo exhaustiva lluvia y frío.";
        this.bodyText = texts[r.nextInt(4)];

    }


    protected Mail(Parcel in) {
        mailId = in.readString();
        senderName = in.readString();
        senderAddress = in.readString();
        receiverAddress = in.readString();
        cc = in.readString();
        cco = in.readString();
        subject = in.readString();
        bodyText = in.readString();
        dateTimeInMs = in.readLong();
        byte tmpIsImportant = in.readByte();
        isImportant = tmpIsImportant == 0 ? null : tmpIsImportant == 1;
        byte tmpIsViewed = in.readByte();
        isViewed = tmpIsViewed == 0 ? null : tmpIsViewed == 1;
    }

    public static final Creator<Mail> CREATOR = new Creator<Mail>() {
        @Override
        public Mail createFromParcel(Parcel in) {
            return new Mail(in);
        }

        @Override
        public Mail[] newArray(int size) {
            return new Mail[size];
        }
    };

    @Override
    public String getId() {
        return mailId;
    }



    @Override
    public String getSenderName() {
        return senderName;
    }

    @Override
    public void setSenderName(String name) {
        this.senderName = name;
    }

    @Override
    public String getBodyText() {
        return bodyText;
    }

    @Override
    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
    
    public String getFolderId(){
        return folderId;
    };

    @Override
    public long getDateTimeInMs() {
        return dateTimeInMs;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isImportant() {
        return isImportant;
    }


    public boolean isViewed() {
        return isViewed;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public void setDateTimeInMs(long dateTimeInMs) {
        this.dateTimeInMs = dateTimeInMs;
    }

    public void setImportant(Boolean important) {
        isImportant = important;
    }

    public void setAttachments(List<IAttachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public void setId() {
        this.mailId = java.util.UUID.randomUUID().toString();
    }

    @Override
    public void setFolderId(String s) {
        this.folderId = s;
    }

    private static String stripAccents(String str) {
        if (str == null) {
            return null;
        }
        char[] array = str.toCharArray();
        for (int index = 0; index < array.length; index++) {
            int pos = ORIGINAL.indexOf(array[index]);
            if (pos > -1) {
                array[index] = REPLACEMENT.charAt(pos);
            }
        }
        return new String(array);
    }


    @Override
    public String toString() {
        return
                " Sender:'" + senderName + '\'' +
                        ", Sender Address:'" + senderAddress + '\'' +
                        ", dateTimeInMs:'" + dateTimeInMs + '\'' +
                        ", subject:'" + subject + '\'' +
                        '}';
    }


    @Override
    public int compareTo(IMail m) {
        int estado = -1;
        if (this.dateTimeInMs == m.getDateTimeInMs()) {
            //Los objetos son iguales
            estado = 0;
        } else if (this.dateTimeInMs > m.getDateTimeInMs()) {
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
        dest.writeString(senderName);
        dest.writeString(senderAddress);
        dest.writeString(receiverAddress);
        dest.writeString(cc);
        dest.writeString(cco);
        dest.writeString(subject);
        dest.writeString(bodyText);
        dest.writeLong(dateTimeInMs);
        dest.writeByte((byte) (isImportant == null ? 0 : isImportant ? 1 : 2));
        dest.writeByte((byte) (isViewed == null ? 0 : isViewed ? 1 : 2));
    }
}