package com.celerapps.celermail.inboxGroup.search.presenter;

import com.celerapps.celermail.inboxGroup.search.interfaces.SearchInteractor;
import com.celerapps.celermail.inboxGroup.search.interfaces.SearchPresenter;
import com.celerapps.celermail.inboxGroup.search.models.SearchInteractorImpl;
import com.celerapps.celermail.inboxGroup.search.views.SearchActivity;

public class SearchPresenterImpl implements SearchPresenter {

    private SearchInteractor interactor;
    private SearchActivity view;

    public SearchPresenterImpl(SearchActivity view) {
        this.interactor = new SearchInteractorImpl(this);
        this.view = view;
    }
    @Override
    public void start() {

    }
}
