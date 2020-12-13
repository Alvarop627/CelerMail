package com.celerapps.celermail.inboxGroup.mailViewer.presenter;


import android.content.Context;

import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.FragmentNavigation;
import com.celerapps.celermail.inboxGroup.mailViewer.interfaces.IMailInteractor;
import com.celerapps.celermail.inboxGroup.mailViewer.interfaces.IMailVP;
import com.celerapps.celermail.inboxGroup.mailViewer.models.MailInteractor;
import com.celerapps.celermail.shared.interfaces.IMail;

public class MailPresenter implements IMailVP.Presenter, FragmentNavigation.Presenter {

    private IMailInteractor interactor;
    private IMailVP.View view;

    public MailPresenter(IMailVP.View view, Context context) {
        this.interactor = new MailInteractor(this, context);
        this.view = view;
    }
    @Override
    public void start() {

    }

    @Override
    public void addFragment(BaseFragment fragment, int idContainer) {

    }

    @Override
    public IMail getMail(String mailId) {
        return interactor.getMail(mailId);
    }
}
