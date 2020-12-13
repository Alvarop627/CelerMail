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

        //CREATION OF MAIL LIST

        mailListType = new TypeToken<ArrayList<Mail>>() {
        }.getType();

        this.context = context;

        generatedJsonMailList = context.getSharedPreferences("MockPreferences", Context.MODE_PRIVATE).getString("mailList", "");

        //generatedJsonMailList = ""; quitar comentarios si quieres crear correos nuevos.
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


        /*
        //CREATION OF FOLDER LIST
        Type folderListType = new TypeToken<ArrayList<ISelectedMailFolder>>() {
        }.getType();

         generatedJsonFolderList = PreferenceManager.
                getDefaultSharedPreferences(context).getString("folderList", "");

        if (generatedJsonFolderList == null||generatedJsonFolderList.equals("")) {
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

           /* String jsonFolderList = gson.toJson(mailList);
            PreferenceManager.getDefaultSharedPreferences(context).edit()
                    .putString("folderList", jsonFolderList).apply();

            generatedJsonFolderList = PreferenceManager.
                    getDefaultSharedPreferences(context).getString("folderList", "");

        }

        folderList =Collections.unmodifiableList(gson.fromJson(generatedJsonFolderList,folderListType));
*/
    }

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
