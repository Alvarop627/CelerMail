package com.celerapps.celermail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * the base fragment implements the navigation view
 * to set the default methods implementation
 */
public abstract class BaseFragment extends Fragment implements FragmentNavigation.View {

    // the root view
    protected View rootView;
    /**
     * navigation presenter instance
     * declared in base for easier access
     */
    protected FragmentNavigation.Presenter navigationPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        rootView = inflater.inflate(getLayout(), container, false);
        return rootView;
    }

    protected abstract int getLayout();

    /**
     * set the navigation presenter instance
     * @param presenter
     */
    @Override
    public void attachPresenter(FragmentNavigation.Presenter presenter) {
        navigationPresenter = presenter;
    }

}