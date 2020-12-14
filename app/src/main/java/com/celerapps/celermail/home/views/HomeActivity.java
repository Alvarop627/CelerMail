package com.celerapps.celermail.home.views;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.R;
import com.celerapps.celermail.home.interfaces.IHomeVP;
import com.celerapps.celermail.home.presenter.HomePresenter;
import com.google.android.material.navigation.NavigationView;

/**
 * Esta clase define la pantalla inicial de la aplicación.
 *
 * @author: Álvaro Reina Carrizosa
 */

public class HomeActivity extends AppCompatActivity implements IHomeVP.View {

    private AppBarConfiguration mAppBarConfiguration;
    private IHomeVP.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter = new HomePresenter(this);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_mail);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_login, R.id.nav_terms, R.id.nav_privacy)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void setFragment(BaseFragment fragment, int idContainer) {

    }

    @Override
    public void setPresenter(IHomeVP.Presenter presenter) {

    }

    public IHomeVP.Presenter getPresenter() {
        return presenter;
    }
}

