package com.celerapps.celermail.home.interfaces;


import android.app.Activity;
import android.content.Context;

import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.BasePresenter;
import com.celerapps.celermail.BaseView;

public interface IHomeVP {

    interface View extends BaseView<Presenter> {

        void setFragment(BaseFragment fragment, int idContainer);
    }

    interface Presenter extends BasePresenter {
        void signIn(String email, String password);

    }

}
