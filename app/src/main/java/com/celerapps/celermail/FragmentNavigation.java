package com.celerapps.celermail;

/**
 * Created by dsdmsa on 4/8/17.
 */

public interface FragmentNavigation {
    interface View {
        void attachPresenter(Presenter presenter);

    }

    interface Presenter {
        void addFragment(BaseFragment fragment, int idContainer);
    }
}
