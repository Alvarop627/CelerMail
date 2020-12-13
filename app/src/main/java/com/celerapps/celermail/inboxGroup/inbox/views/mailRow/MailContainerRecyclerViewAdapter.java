package com.celerapps.celermail.inboxGroup.inbox.views.mailRow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.celerapps.celermail.R;
import com.celerapps.celermail.inboxGroup.inbox.views.InboxActivity;
import com.celerapps.celermail.inboxGroup.mailViewer.views.MailActivity;
import com.celerapps.celermail.shared.interfaces.IMailContainer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MailContainerRecyclerViewAdapter extends RecyclerView.Adapter<MailContainerRecyclerViewAdapter.ViewHolder> {

    private List<IMailContainer> headersList;
    private List<CheckBox> checkBoxes = new ArrayList<>();

    public MailContainerRecyclerViewAdapter(List<IMailContainer> headersList) {
        this.headersList = headersList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = this.headersList.get(position);
        holder.mSender.setText(holder.mItem.getSender());
        holder.mSubject.setText(this.headersList.get(position).getSubject());
        holder.mBodyText.setText(this.headersList.get(position).getBodyText());
        holder.mView.setBackgroundResource(getMailBackground(holder.mItem));


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(headersList.get(position).getDateTimeInMs());

        SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatterDate.format(calendar.getTime());
        holder.mDate.setText(date);

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        holder.mTime.setText(timeFormat.format(calendar.getTime()));
        /*
        holder.mView.setOnLongClickListener(view -> {
            holder.mView.setBackgroundResource(R.color.dodgerBlue);
            return true;
        });*/

        if (!checkBoxes.contains(holder.mCheckBox)) {
            checkBoxes.add(holder.mCheckBox);
        }

        if (holder.mItem.isImportant()) {
            holder.mFlagImportantMail.setVisibility(View.VISIBLE);
        } else {
            holder.mFlagImportantMail.setVisibility(View.GONE);
        }

        holder.mBtnShowMailOptions.setOnClickListener(v -> {
            InboxActivity.showMailOptionsMenu((Button) v);
        });

        holder.layoutMailContainer.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("mailId", holder.mItem.getMailId());
                Intent intent = new Intent(v.getContext(), MailActivity.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);



        });

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            boolean checked = false;

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    InboxActivity.showInboxButtons();
                } else {

                    for (CheckBox c : checkBoxes) {
                        if (c.isChecked()) {
                            checked = true;
                        }
                    }

                    if (checked == false) {
                        InboxActivity.hideInboxButtons();
                    }

                }


            }

        });

        holder.mMailFooter.setBackgroundResource(getMailFooterBackground(holder.mItem));


    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(headersList.size()>0){
            size=headersList.size();
        }
        return size;
    }

    public int getMailFooterBackground(IMailContainer m) {
        int background = 0;
            if (m.isImportant()) {
                background = R.drawable.border_important_mail_footer;
            } else if (m.isViewed() && !m.isImportant()) {
                background = R.drawable.border_viewed_mail_footer;
            } else {
                background = R.drawable.border_not_viewed_mail_footer;
            }

        return background;
    }

    public int getMailBackground(IMailContainer m) {
        return R.drawable.border_mail;
    }

    public List<IMailContainer> getHeadersList() {
        return headersList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final CheckBox mCheckBox;
        final TextView mSender;
        final TextView mDate;
        final TextView mTime;
        final TextView mSubject, mBodyText;
        final Button mBtnShowMailOptions;
        final ImageView mFlagImportantMail;
        final LinearLayout mMailFooter, layoutMailContainer;
        IMailContainer mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mCheckBox = view.findViewById(R.id.mCheckBox);
            mSender = view.findViewById(R.id.mName);
            mDate = view.findViewById(R.id.mDate);
            mTime = view.findViewById(R.id.mTime);
            mSubject = view.findViewById(R.id.mSubject);
            mBodyText = view.findViewById(R.id.mBodyText);
            mBtnShowMailOptions = view.findViewById(R.id.btnShowMailOptions);
            mMailFooter = view.findViewById(R.id.mailFooter);
            mFlagImportantMail = view.findViewById(R.id.flagImportantMail);
            layoutMailContainer = view.findViewById(R.id.layoutMailContainer);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mSender.getText() + "'" + " '" + mSubject.getText() + "'" + " '" + mDate.getText() + "'" + " '" + mTime.getText() + "'";
        }
    }
}
