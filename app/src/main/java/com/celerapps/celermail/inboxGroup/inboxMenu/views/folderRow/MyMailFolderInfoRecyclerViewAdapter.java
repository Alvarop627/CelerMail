package com.celerapps.celermail.inboxGroup.inboxMenu.views.folderRow;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;


import com.celerapps.celermail.R;
import com.celerapps.celermail.inboxGroup.inbox.views.InboxActivity;
import com.celerapps.celermail.inboxGroup.inboxMenu.views.InboxMenuActivity;
import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;

import java.util.List;

public class MyMailFolderInfoRecyclerViewAdapter extends RecyclerView.Adapter<MyMailFolderInfoRecyclerViewAdapter.ViewHolder> {

    private List<IMailFolderInfo> mailFolderInfoList;

    public MyMailFolderInfoRecyclerViewAdapter(List<IMailFolderInfo> mailFolderInfoList) {
        this.mailFolderInfoList = mailFolderInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mail_folder_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mailFolderInfoList.get(position);
        holder.mFolderName.setText(holder.mItem.getFolderName());
        holder.mView.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putString("folderId", holder.mItem.getFolderId());
            b.putBoolean("isUserCreated", holder.mItem.isUserCreated());
            b.putString("folderName",holder.mItem.getFolderName());
            Intent intent = new Intent(v.getContext(), InboxActivity.class);
            intent.putExtras(b);
            v.getContext().startActivity(intent);
        });

        if(holder.mItem.getFolderId().equals(InboxMenuActivity.getSelectedFolderId())){
            holder.mView.setBackgroundResource(R.color.darkestGrey);
        }

        String text = holder.mFolderName.getText().toString();
        if (holder.mView.getResources().getString(R.string.inbox).equals(text)) {
            holder.mImg.setImageResource(R.drawable.inbox);
        } else if (holder.mView.getResources().getString(R.string.archived).equals(text)) {
            holder.mImg.setImageResource(R.drawable.archived);
        } else if (holder.mView.getResources().getString(R.string.drafts).equals(text)) {
            holder.mImg.setImageResource(R.drawable.deleted);
        } else if (holder.mView.getResources().getString(R.string.spam).equals(text)) {
            holder.mImg.setImageResource(R.drawable.spam);
        } else if (holder.mView.getResources().getString(R.string.sent).equals(text)) {
            holder.mImg.setImageResource(R.drawable.send);
        } else if (holder.mView.getResources().getString(R.string.bin).equals(text)) {
            holder.mImg.setImageResource(R.drawable.bin);
        } else if(!holder.mItem.isUserCreated()){
            holder.mImg.setImageResource(R.drawable.archived);
        }

        if(holder.mItem.isUserCreated()){
            holder.mBtnFolderOptions.setVisibility(View.VISIBLE);
        }

        holder.mBtnFolderOptions.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(v.getContext(), holder.mBtnFolderOptions);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.folder_options_menu, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getTitle().toString()){
                        case "Cambiar nombre":
                            holder.mItem.getFolderId();

                            break;
                        case "Eliminar":
                            holder.mItem.getFolderId();
                            break;
                    }
                    return true;
                }
            });
            popup.setGravity(Gravity.END);
            popup.show();//showing popup menu
        });
    }

    @Override
    public int getItemCount() {
        return mailFolderInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ImageView mImg;
        public final TextView mFolderName;
        public final ImageButton mBtnFolderOptions;
        public IMailFolderInfo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImg = view.findViewById(R.id.imgMailFolderInfo);
            mFolderName = (TextView) view.findViewById(R.id.txtFolderName);
            mBtnFolderOptions = view.findViewById(R.id.btnFolderStateOptions);

        }

    }
}