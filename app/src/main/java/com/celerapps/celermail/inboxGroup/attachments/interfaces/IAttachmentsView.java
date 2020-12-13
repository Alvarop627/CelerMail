package com.celerapps.celermail.inboxGroup.attachments.interfaces;

import androidx.fragment.app.Fragment;

import com.celerapps.celermail.BaseView;

public interface IAttachmentsView extends BaseView<IAttachmentsPresenter> {
    void setFragment(Fragment fragment, int idContainer);
    static void updateFragment() {

    }
}
