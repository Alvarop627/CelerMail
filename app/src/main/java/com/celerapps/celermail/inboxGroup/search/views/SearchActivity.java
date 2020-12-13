package com.celerapps.celermail.inboxGroup.search.views;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;


import com.celerapps.celermail.R;
import com.celerapps.celermail.inboxGroup.search.interfaces.SearchPresenter;
import com.celerapps.celermail.inboxGroup.search.interfaces.SearchView;
import com.celerapps.celermail.inboxGroup.search.presenter.SearchPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView {

    private Spinner spEmailSections;
    private SearchPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        spEmailSections = findViewById(R.id.spEmailSections);
        ArrayAdapter<CharSequence> adapterSpEmailSections = ArrayAdapter.createFromResource(this, R.array.emailSections,R.layout.support_simple_spinner_dropdown_item);
        //adapterSpEmailSections.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spEmailSections.setAdapter(adapterSpEmailSections);



        final ListView list = findViewById(R.id.listViewSearchResults);
        List<String> arrayList = new ArrayList<>();
        addList(list, arrayList);

        SwitchCompat switchSearch = findViewById(R.id.switchSearch);
        switchSearch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                buttonView.setText(R.string.important);
            }else{
                buttonView.setText(R.string.notImpotant);
            }
        });
        presenter = new SearchPresenterImpl(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void addList(final ListView list, List arrayList){
        for(int i = 0; i<20;i++){
            arrayList.add("Mail "+i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener((parent, view, position, id) -> {
            String clickedItem=(String) list.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),clickedItem,Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void setPresenter(SearchPresenter presenter) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
