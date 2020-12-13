package com.celerapps.celermail.inboxGroup.inboxMenu.presenter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.widget.TextView;

import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.FragmentNavigation;
import com.celerapps.celermail.R;
import com.celerapps.celermail.inboxGroup.inboxMenu.interfaces.IInboxMenuInteractor;
import com.celerapps.celermail.inboxGroup.inboxMenu.interfaces.IInboxMenuVP;
import com.celerapps.celermail.inboxGroup.inboxMenu.models.InboxMenuInteractor;
import com.celerapps.celermail.inboxGroup.inboxMenu.views.folderRow.MailFolderInfoFragment;
import com.celerapps.celermail.shared.interfaces.IMailAccountHeader;
import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;

import java.util.ArrayList;
import java.util.List;

public class InboxMenuPresenter implements IInboxMenuVP.Presenter, FragmentNavigation.Presenter {

    private IInboxMenuInteractor interactor;
    private IInboxMenuVP.View view;
    private Context context;

    public InboxMenuPresenter(IInboxMenuVP.View view, Context context) {
        this.interactor = new InboxMenuInteractor(this, context);
        this.view = view;
        this.context = context;

    }

    @Override
    public void start() {

    }

    @Override
    public void addFragment(BaseFragment fragment, int idContainer) {

    }

    public IMailAccountHeader getMailAccountHeader() {
        return interactor.getMailAccountHeader();
    }

    public Bitmap getRoundedCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }

    public List<IMailFolderInfo> getMailFolderInfos(boolean isUserCreated) {
        return interactor.getMailFolderInfo(isUserCreated);
    }



    @Override
    public String getSelectedAccountName() {
        return interactor.getSelectedAccount().getName();
    }

    @Override
    public String getSelectedAccountEmail() {
        return interactor.getSelectedAccount().getEmailAddress();
    }
    @Override
    public String getSelectedFolderId() {
        return interactor.getSelectedAccount().getFolderId();
    }


    @Override
    public void createAccountFolder(String folderName) {
    interactor.createAccountFolder(folderName);
    }

    @Override
    public void setMailAccountInfo(TextView accountName, TextView accountEmailAddress) {
        accountName.setText(this.getSelectedAccountName());
        accountEmailAddress.setText(this.getSelectedAccountEmail());
    }

    @Override
    public void signOut() {
        interactor.signOut();
    }
}
