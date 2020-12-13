package com.celerapps.celermail.inboxGroup.inboxMenu.views.folderRow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.R;
import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MailFolderInfoFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<IMailFolderInfo> mailFolderInfoList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MailFolderInfoFragment() {
    }

    public MailFolderInfoFragment(List<IMailFolderInfo> mailFolderInfoList) {
        this.mailFolderInfoList = mailFolderInfoList;
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MailFolderInfoFragment newInstance(int columnCount) {
        MailFolderInfoFragment fragment = new MailFolderInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_mail_folder_info_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyMailFolderInfoRecyclerViewAdapter(this.getMailFolderInfoList()));
        }
        return view;
    }

    @Override
    protected int getLayout() {
        return this.getLayout();
    }


    public List<IMailFolderInfo> getMailFolderInfoList() {
        return mailFolderInfoList;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}