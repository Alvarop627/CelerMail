package com.celerapps.celermail.registerGroup.privacy.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.celerapps.celermail.R;
import com.celerapps.celermail.registerGroup.privacy.interfaces.IPrivacyVP;
import com.celerapps.celermail.registerGroup.privacy.presenter.PrivacyPresenter;
import com.celerapps.celermail.registerGroup.regForm.views.RegFormActivity;
import com.celerapps.celermail.registerGroup.terms.views.TermsActivity;


public class PrivacyActivity extends AppCompatActivity implements IPrivacyVP.View {

    private IPrivacyVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPrivacyActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btnPrivacyNext = findViewById(R.id.btnPrivacyNext);
        Button btnPrivacyBack = findViewById(R.id.btnPrivacyBack);
        ScrollView scrollViewPrivacy = findViewById(R.id.scrollViewPrivacy);
        scrollViewPrivacy.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            View view = (View) scrollViewPrivacy.getChildAt(scrollViewPrivacy.getChildCount()-1);

            // Calculate the scrolldiff
            int diff = (view.getBottom()-(scrollViewPrivacy.getHeight()+scrollViewPrivacy.getScrollY()));

            // if diff is zero, then the bottom has been reached
            if( diff == 0 )
            {
                btnPrivacyNext.setBackgroundResource(R.drawable.btn_next_shape);
                btnPrivacyNext.setEnabled(true);
                // notify that we have reached the bottom
                //Log.d(ScrollTest.LOG_TAG, "MyScrollView: Bottom has been reached" );
            }
        });

        btnPrivacyNext.setOnClickListener(v -> {
            startActivity(new Intent(this, RegFormActivity.class));
        });

        btnPrivacyBack.setOnClickListener(v -> {
            startActivity(new Intent(this, TermsActivity.class));
        });

        presenter = new PrivacyPresenter(this);
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
    public void setPresenter(IPrivacyVP.Presenter presenter) {

    }
}
