package com.celerapps.celermail.inboxGroup.search.models;


import com.celerapps.celermail.inboxGroup.search.interfaces.SearchInteractor;
import com.celerapps.celermail.inboxGroup.search.presenter.SearchPresenterImpl;

public class SearchInteractorImpl implements SearchInteractor {

    private SearchPresenterImpl presenter;

    public SearchInteractorImpl(SearchPresenterImpl presenter) {
        this.presenter = presenter;
    }
}
