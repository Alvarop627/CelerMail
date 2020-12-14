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

    /**
     * Esta clase define una cuenta de correo electrónico
     *
     * @author: Álvaro Reina Carrizosa
     */

    public MailAccount() {

    }

    /**
     * @param accountId es el identificador de la cuenta
     * @param name  es el nombre del usuario de la cuenta
     * @param emailAddress es la dirección de correo electrónico de la cuenta
     * @param password es la contraseña de la cuenta
     * @param gender es el género del usuario de la cuenta
     * @param countrie es el país del usuario de la cuenta
     * @param image es la imagen de perfil de la cuenta
     * @param defaultFolders son las carpetas que vienen por defecto en todas las cuentas
     * @param customFolders son las carpetas personalizadas que crea el usuario de la cuenta
     * @param folderId es el identificador de la actual carpeta seleccionada
     * @param birthdate es la fecha de nacimiento del usuario de la cuenta
     * @param phone es el número de teléfono asociado a la cuenta
     */
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

    /**
     *
     * @return el identificador de la cuenta
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     *
     * @return el nombre del usuario de la cuenta
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return el email de la cuenta
     */
    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @return la contraseña de la cuenta
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return la foto de perfil de la cuenta
     */
    public Bitmap getImage() {
        return image;
    }
    /**
     *
     * @return el género del usuario de la cuenta
     */
    public String getGender() {
        return gender;
    }
    /**
     *
     * @return el país del usuario de la cuenta
     */
    public String getCountrie() {
        return countrie;
    }
    /**
     *
     * @return la fecha de nacimiento del usuario de la cuenta
     */
    public long getBirthdate() {
        return birthdate;
    }
    /**
     *
     * @return el número de teléfono asociado a la cuenta
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param defaultFolders modifica las carpetas que vienen por defecto
     */
    public void setDefaultFolders(List<IMailFolderInfo> defaultFolders) {
        this.defaultFolders = defaultFolders;
    }

    /**
     *
     * @param customFolders modifica las carpetas que crea el usuario
     */
    public void setCustomFolders(List<IMailFolderInfo> customFolders) {
        this.customFolders = customFolders;
    }

    /**
     *
     * @return las carpetas que vienen por defecto
     */
    @Override
    public List<IMailFolderInfo> getDefaultFolders() {
        return defaultFolders;
    }

    /**
     *
     * @return las carpetas que crea el usuario
     */
    public List<IMailFolderInfo> getCustomFolders() {
        return customFolders;
    }

    /**
     *
     * @return el id de la carpeta seleccionada actualmente
     */
    @Override
    public String getFolderId() {
        return folderId;
    }

    /**
     *
     * @param image modifica la imagen de la cuenta
     */
    public void setImage(Bitmap image) {
        this.image = image;
    }

    /**
     *
     * @return un objeto que contiene el id de la cuenta y la foto. Se usa para seleccionar la cuenta actual.
     */
    public IMailAccountHeader getMailAccountHeader() {
        IMailAccountHeader mailAccountHeader = null;
        if(this.getImage()!=null){
             mailAccountHeader = new MailAccountHeader(this.getAccountId(), this.getImage());
        }
        return mailAccountHeader;
    }


}
