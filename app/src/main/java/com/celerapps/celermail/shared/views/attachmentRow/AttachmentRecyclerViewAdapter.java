package com.celerapps.celermail.shared.views.attachmentRow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.celerapps.celermail.R;
import com.celerapps.celermail.inboxGroup.attachments.views.AttachmentsActivity;
import com.celerapps.celermail.shared.interfaces.IAttachment;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link IAttachment}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AttachmentRecyclerViewAdapter extends RecyclerView.Adapter<AttachmentRecyclerViewAdapter.ViewHolder> {

    private final List<IAttachment> attachmentsList;

    public AttachmentRecyclerViewAdapter(List<IAttachment> items) {
        attachmentsList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_attachment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = (IAttachment) attachmentsList.get(position);
        holder.mImgAttachmentType = null;
        holder.mTxtAttachment.setText(holder.mItem.getName());
        holder.mBtnRmAttachment.setOnClickListener(v -> {
            if (AttachmentsActivity.getListAttachments().contains(holder.mItem)) {
                //selectedCertifiers.remove(holder.mItem);
                AttachmentsActivity.getListAttachments().remove(holder.mItem);
                AttachmentsActivity.updateFragment();

            }
        });

    }

    @Override
    public int getItemCount() {
        return attachmentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ImageView mImgAttachmentType;
        public final TextView mTxtAttachment;
        public final ImageButton mBtnRmAttachment;
        public IAttachment mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view; mImgAttachmentType = view.findViewById(R.id.imgAttachmentType);
            mTxtAttachment = view.findViewById(R.id.txtAttachment);
            mBtnRmAttachment = view.findViewById(R.id.btnRmAttachment);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mItem.getName() + "'"+ " '" + mItem.getType() + "'";
        }
    }
}