package com.celerapps.celermail.shared.models;

import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;

/**
 * Esta clase define una carpeta de la bandeja de entrada del correo electrónico.
 *
 * @author: Álvaro Reina Carrizosa
 */

public class MailFolderInfo implements IMailFolderInfo {

    private String folderId, folderName;
    private boolean isUserCreated;

    /**
     *
     * @param folderId el identificador de la carpeta
     * @param folderName el nombre de la carpeta
     * @param isUserCreated indica si la carpeta viene por defecto o es creada por el usuario
     */
    public MailFolderInfo(String folderId, String folderName, boolean isUserCreated) {
        this.folderId = folderId;
        this.folderName = folderName;
        this.isUserCreated = isUserCreated;
    }

    public MailFolderInfo(String folderName) {
        this.folderId = java.util.UUID.randomUUID().toString();
        this.folderName = folderName;
        this.isUserCreated = true;
    }

    public MailFolderInfo() {
    }

    @Override
    public String getFolderId() {
        return folderId;
    }

    @Override
    public String getFolderName() {
        return folderName;
    }

    @Override
    public boolean isUserCreated() {
        return isUserCreated;
    }
}
