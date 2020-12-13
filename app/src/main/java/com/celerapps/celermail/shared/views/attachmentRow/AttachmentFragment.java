package com.celerapps.celermail.shared.views.attachmentRow;

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
import com.celerapps.celermail.shared.interfaces.IAttachment;

import java.util.List;


/**
 * A fragment representing a list of Items.
 */
public class AttachmentFragment extends Fragment {

    private List<IAttachment> attachmentsList;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AttachmentFragment() {
    }

    public AttachmentFragment(List<IAttachment> attachmentsList) {
        this.attachmentsList = attachmentsList;
    }


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AttachmentFragment newInstance(int columnCount) {
        AttachmentFragment fragment = new AttachmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_attachment_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new AttachmentRecyclerViewAdapter(this.attachmentsList));
        }
        return view;
    }

    public List<IAttachment> getAttachmentsList() {
        return attachmentsList;
    }
}