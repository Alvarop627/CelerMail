package com.celerapps.celermail.shared.views.mailHistoryRow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.celerapps.celermail.R;
import com.celerapps.celermail.shared.interfaces.IMail;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MailHistoryFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private List<IMail> mailHistoryList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MailHistoryFragment(List<IMail> mailHistoryList) {
        this.mailHistoryList = mailHistoryList;
    }

    public MailHistoryFragment() {

    }

    // TODO: Customize parameter initialization
    public static MailHistoryFragment newInstance(int columnCount) {
        MailHistoryFragment fragment = new MailHistoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mail_history_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MailHistoryRecyclerViewAdapter(this.getMailHistoryList()));
        }
        return view;
    }

    private List<IMail> getMailHistoryList() {
        return mailHistoryList;
    }

    void setMailHistoryList(List<IMail> mailHistoryList) {
        this.mailHistoryList = mailHistoryList;
    }
}