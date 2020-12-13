package com.celerapps.celermail.registerGroup.terms.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.celerapps.celermail.R;
import com.celerapps.celermail.home.views.HomeActivity;
import com.celerapps.celermail.registerGroup.privacy.views.PrivacyActivity;
import com.celerapps.celermail.registerGroup.terms.interfaces.ITermsVP;
import com.celerapps.celermail.registerGroup.terms.presenter.TermsPresenter;


public class TermsActivity extends AppCompatActivity implements ITermsVP.View {

    private ITermsVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTermsActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btnTermsToPrivacy = findViewById(R.id.btnTermsToPrivacy);


        ScrollView scrollViewTerms = findViewById(R.id.scrollViewTerms);
        scrollViewTerms.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            View view = (View) scrollViewTerms.getChildAt(scrollViewTerms.getChildCount()-1);

            // Calculate the scrolldiff
            int diff = (view.getBottom()-(scrollViewTerms.getHeight()+scrollViewTerms.getScrollY()));

            // if diff is zero, then the bottom has been reached
            if( diff == 0 )
            {
                btnTermsToPrivacy.setBackgroundResource(R.drawable.btn_next_shape);
                btnTermsToPrivacy.setEnabled(true);
                // notify that we have reached the bottom
                //Log.d(ScrollTest.LOG_TAG, "MyScrollView: Bottom has been reached" );
            }
        });

        presenter = new TermsPresenter(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void setPresenter(ITermsVP.Presenter presenter) {

    }

    public void goToPrivacy(View view){
        Intent intent = new Intent(this, PrivacyActivity.class);
        startActivity(intent);
    }

    public void goToHome(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
