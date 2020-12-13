package com.celerapps.celermail.inboxGroup.inboxMenu.interfaces;

import android.graphics.Bitmap;
import android.widget.TextView;


import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.BasePresenter;
import com.celerapps.celermail.BaseView;
import com.celerapps.celermail.shared.interfaces.IMailAccountHeader;
import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;

import java.util.List;

public interface IInboxMenuVP {

    interface View extends BaseView<Presenter> {
        void updateFragment(String tag);

        void setFragment(BaseFragment fragment, int idContainer, String tag);
        void updateTxtAccountInfo();
    }

    interface Presenter extends BasePresenter {
        IMailAccountHeader getMailAccountHeader();
        Bitmap getRoundedCroppedBitmap(Bitmap bitmap);
        List<IMailFolderInfo> getMailFolderInfos(boolean isUserCreated);
        String getSelectedAccountName();
        String getSelectedAccountEmail();
        void createAccountFolder(String folderName);
        void setMailAccountInfo(TextView accountName, TextView accountEmailAddress);
        String getSelectedFolderId();

        void signOut();
    }
}
