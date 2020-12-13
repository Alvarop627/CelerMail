package com.celerapps.celermail.inboxGroup.attachments.models;


import com.celerapps.celermail.inboxGroup.attachments.interfaces.IAttachmentsInteractor;
import com.celerapps.celermail.inboxGroup.attachments.presenter.AttachmentsPresenter;
import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.interfaces.IMailAccount;
import com.celerapps.celermail.utils.BaseActivity;
import com.celerapps.databaseconnector.IDatabaseConnector;
import com.celerapps.databaseconnector.IMailService;

public class AttachmentsInteractor implements IAttachmentsInteractor {

    private AttachmentsPresenter presenter;
    private IDatabaseConnector databaseConnector;
    private IMailService mailServiceMock;

    public AttachmentsInteractor(AttachmentsPresenter presenter) {
        this.presenter = presenter;
        this.databaseConnector = IDatabaseConnector.getInstance();
        this.mailServiceMock = IMailService.getInstance(BaseActivity.context());
    }

    @Override
    public IMailAccount getSelectedAccount() {
        return databaseConnector.getSelectedMailAccount();
    }

    @Override
    public void createMail(IMail mMail) {
        mailServiceMock.createMail(mMail);
        //Lo siguiente es funcional en la base de datos pero como no puedo traer los mails de la base de datos sólo se vería creado en la base de datos, no en la app.
        //databaseConnector.createMail(mMail);
    }
}
