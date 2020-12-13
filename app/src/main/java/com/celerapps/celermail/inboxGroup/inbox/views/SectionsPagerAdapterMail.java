package com.celerapps.celermail.inboxGroup.inbox.views;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.celerapps.celermail.shared.models.FragmentDescriptor;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapterMail extends FragmentStateAdapter {

    private FragmentDescriptor[] fragmentsDescriptorCollection;


    public SectionsPagerAdapterMail(InboxActivity inboxActivity, FragmentDescriptor[] fragmentsDescriptorCollection) {
        super(inboxActivity);
        this.fragmentsDescriptorCollection = fragmentsDescriptorCollection;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentsDescriptorCollection[position].getFragment();
    }

    @Override
    public int getItemCount() {
        return fragmentsDescriptorCollection.length;
    }
}