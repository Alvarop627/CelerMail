package com.celerapps.celermail.inboxGroup.inboxMenu.views.mailAccountHeaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.celerapps.celermail.R;
import com.celerapps.celermail.inboxGroup.inboxMenu.views.InboxMenuActivity;
import com.celerapps.celermail.shared.interfaces.IMailAccountHeader;


public class MailAccountHeaderRecyclerViewAdapter extends RecyclerView.Adapter<MailAccountHeaderRecyclerViewAdapter.ViewHolder> {

    private IMailAccountHeader[] mailAccountHeadersList;

    public MailAccountHeaderRecyclerViewAdapter(IMailAccountHeader[] mailAccountHeadersList) {
        this.mailAccountHeadersList = mailAccountHeadersList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mail_account_header, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = this.mailAccountHeadersList[position];
        Bitmap bitmap = holder.mItem.getImage();
        Bitmap b = InboxMenuActivity.getPresenter().getRoundedCroppedBitmap(bitmap);
        holder.mButton.setImageBitmap(b);
        holder.mButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        holder.mButton.setAdjustViewBounds(true);

    }

    @Override
    public int getItemCount() {
        return mailAccountHeadersList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ImageButton mButton;
        IMailAccountHeader mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mButton = view.findViewById(R.id.btnMailAccountHeader);

        }


    }
}