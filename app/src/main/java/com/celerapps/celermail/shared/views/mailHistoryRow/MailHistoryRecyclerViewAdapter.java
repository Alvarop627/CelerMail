package com.celerapps.celermail.shared.views.mailHistoryRow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.celerapps.celermail.R;
import com.celerapps.celermail.shared.interfaces.IMail;

import java.util.ArrayList;
import java.util.List;


public class MailHistoryRecyclerViewAdapter extends RecyclerView.Adapter<MailHistoryRecyclerViewAdapter.ViewHolder> {

    private List<IMail> mailHistoryList = new ArrayList<>();

    public MailHistoryRecyclerViewAdapter(List<IMail> mailHistoryList){
        this.mailHistoryList = mailHistoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mail_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mailHistoryList.get(position);
        holder.mSender.setText(mailHistoryList.get(position).getSenderName());
        holder.mSubject.setText(mailHistoryList.get(position).getSubject());
        holder.mBodyText.setText(mailHistoryList.get(position).getBodyText());
    }

    @Override
    public int getItemCount() {
        return mailHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        final TextView mSender;
        final TextView mSubject,mBodyText;
        public IMail mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mSender = view.findViewById(R.id.mHistorySender);
            mSubject = view.findViewById(R.id.mHistorySubject);
            mBodyText = view.findViewById(R.id.mHistoryBodyText);
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "mSender=" + mSender +
                    ", mSubject=" + mSubject +
                    ", mBodyText=" + mBodyText +
                    '}';
        }
    }
}