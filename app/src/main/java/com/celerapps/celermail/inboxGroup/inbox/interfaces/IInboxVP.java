package com.celerapps.celermail.inboxGroup.inbox.interfaces;


import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.BasePresenter;
import com.celerapps.celermail.BaseView;
import com.celerapps.celermail.shared.interfaces.IMailContainer;

import java.util.List;

public interface IInboxVP {

    interface View extends BaseView<Presenter> {

        void setFragment(BaseFragment fragment, int idContainer);
    }

    interface Presenter extends BasePresenter {

        List<IMailContainer> getMailContainers(String folderId, boolean isUserCreated, boolean isImportant);
    }

}
