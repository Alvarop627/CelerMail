package com.celerapps.databaseconnector;


import android.content.Context;
import android.util.Log;

import com.celerapps.celermail.R;
import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.interfaces.IMailAccount;
import com.celerapps.celermail.shared.interfaces.IMailContainer;
import com.celerapps.celermail.shared.models.Mail;
import com.celerapps.celermail.shared.models.MailContainer;
import com.celerapps.celermail.shared.models.MailFolderInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Esta clase define un servicio ficticio de correo electrónico. Sirve para hacer pruebas sin necesidad de una base de datos. En un futuro se eliminará cuando la base de datos esté completamente implementada.
 *
 * @author: Álvaro Reina Carrizosa
 */

public final class MailServiceMock implements IMailService {

    private List<Mail> mailList = new ArrayList<>();
    private List<MailFolderInfo> folderList = new ArrayList<>();
    private String generatedJsonMailList;
    private Context context;
    List<IMailAccount> mailAccounts = new ArrayList<>();
    private static MailServiceMock mailService;
    private Type mailListType;

    public static MailServiceMock getInstance(Context context) {

        if (mailService == null) {
            mailService = new MailServiceMock(context);
        }
        return mailService;
    }

    public MailServiceMock(Context context) {
        Gson gson = new Gson();

        /**
         * CREATION OF MAIL LIST
         */


        mailListType = new TypeToken<ArrayList<Mail>>() {
        }.getType();

        this.context = context;

        generatedJsonMailList = context.getSharedPreferences("MockPreferences", Context.MODE_PRIVATE).getString("mailList", "");

        /**
         * quitar abajo comentario si quieres crear correos ficticios totalmente nuevos.
         */
        //generatedJsonMailList = "";
        if (generatedJsonMailList == null || generatedJsonMailList.equals("")) {
            for (int i = 0; i <= 90; i++) {
                Mail m = new Mail(new Random());
                mailList.add(m);
            }

            String jsonMailList = gson.toJson(mailList);
            context.getSharedPreferences("MockPreferences", Context.MODE_PRIVATE).edit()
                    .putString("mailList", jsonMailList).apply();

            generatedJsonMailList = context.getSharedPreferences("MockPreferences", Context.MODE_PRIVATE).getString("mailList", "");
        }

        mailList = gson.fromJson(generatedJsonMailList, mailListType);
        Collections.sort(mailList, Collections.reverseOrder());

        /**
         * CREATION OF FOLDER LIST
         */
        /*
        Type folderListType = new TypeToken<ArrayList<ISelectedMailFolder>>() {
        }.getType();

         generatedJsonFolderList = PreferenceManager.
                getDefaultSharedPreferences(context).getString("folderList", "");

        if (generatedJsonFolderList == null||generatedJsonFolderList.equals("")) {
*/
        /**
         * Se crean las carpetas de la base de datos ficticia
         */
        MailFolderInfo inboxFolder = new MailFolderInfo("0", context.getResources().getString(R.string.inbox), false);
        folderList.add(inboxFolder);

        MailFolderInfo archivedFolder = new MailFolderInfo("1", context.getResources().getString(R.string.archived), false);
        folderList.add(archivedFolder);

        MailFolderInfo draftsFolder = new MailFolderInfo("2", context.getResources().getString(R.string.drafts),false);
        folderList.add(draftsFolder);

        MailFolderInfo spamFolder = new MailFolderInfo("3", context.getResources().getString(R.string.spam), false);
        folderList.add(spamFolder);

        MailFolderInfo sentFolder = new MailFolderInfo("4", context.getResources().getString(R.string.sent),false);
        folderList.add(sentFolder);

        MailFolderInfo trashFolder = new MailFolderInfo("5", context.getResources().getString(R.string.bin), false);
        folderList.add(trashFolder);

        /**
         * Como alternativa, para que no desaparezcan las guardo en un json en las preferencias (Esto es temporal)
         */
           /* String jsonFolderList = gson.toJson(mailList);
            PreferenceManager.getDefaultSharedPreferences(context).edit()
                    .putString("folderList", jsonFolderList).apply();

            generatedJsonFolderList = PreferenceManager.
                    getDefaultSharedPreferences(context).getString("folderList", "");

        }


        folderList =Collections.unmodifiableList(gson.fromJson(generatedJsonFolderList,folderListType));
*/
    }

    /**
     * Este método devuelve un email a partir de un identificador que se recibe por parámetro.
     * @param mailId
     * @return
     */
    @Override
    public IMail getMail(String mailId) {
        IMail mail = null;
        for (Mail m : mailList) {
            if (mailId.equals(m.getId())) {
                mail = m;
            }
        }
        return mail;
    }

    /**
     * Este método devuelve los contenedores de los mails que van a aparecer en la bandeja de entrada.
     * Utilizo estos contenedores para no tener que cargar totalmente los emails, solo la información necesaria.
     * @param folderId
     * @return
     */
    @Override
    public List<IMailContainer> getMailContainers(String folderId) {
        List<IMailContainer> containers = new ArrayList<>();
        int pageSize = 10;
        for (Mail m : mailList) {
            if (m.getFolderId().equals(folderId)) {
                if (m != null) {
                    IMailContainer mc = new MailContainer(m);
                    containers.add(mc);
                } else {
                    Log.e("MailServiceMock:","Error al obtener mail containers");
                }
            }
        }
        return containers;
    }

    /**
     * Este método guarda un email que se recibe por parámetro en la base de datos ficticia
     * @param mMail
     */
    @Override
    public void createMail(IMail mMail) {
        if (!mailList.contains(mMail)) {
            mailList.add((Mail) mMail);
            Gson gson = new Gson();
            String jsonMailList = gson.toJson(mailList);
            context.getSharedPreferences("MockPreferences", Context.MODE_PRIVATE).edit()
                    .putString("mailList", jsonMailList).apply();

            String s = context.getSharedPreferences("MockPreferences", Context.MODE_PRIVATE).getString("mailList", "");
            mailList = gson.fromJson(s, mailListType);
            Collections.sort(mailList, Collections.reverseOrder());

        }
    }
}
