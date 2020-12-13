package com.celerapps.celermail.inboxGroup.mailViewer.interfaces;


import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.BasePresenter;
import com.celerapps.celermail.BaseView;
import com.celerapps.celermail.shared.interfaces.IMail;

public interface IMailVP {

    interface View extends BaseView<Presenter> {
        void setFragment(BaseFragment fragment, int idContainer);
    }

    interface Presenter extends BasePresenter {
        IMail getMail(String mailId);
    }
}
