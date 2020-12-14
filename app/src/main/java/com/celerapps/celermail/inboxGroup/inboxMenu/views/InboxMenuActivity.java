package com.celerapps.celermail.inboxGroup.inboxMenu.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.FragmentNavigation;
import com.celerapps.celermail.R;
import com.celerapps.celermail.home.views.HomeActivity;
import com.celerapps.celermail.inboxGroup.inbox.views.InboxActivity;
import com.celerapps.celermail.inboxGroup.inboxMenu.interfaces.IInboxMenuVP;
import com.celerapps.celermail.inboxGroup.inboxMenu.presenter.InboxMenuPresenter;
import com.celerapps.celermail.inboxGroup.inboxMenu.views.folderRow.MailFolderInfoFragment;
import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase define el menú principal de la bandeja de entrada.
 *
 * @author: Álvaro Reina Carrizosa
 */
public class InboxMenuActivity extends AppCompatActivity implements IInboxMenuVP.View {

    public static IInboxMenuVP.Presenter presenter;
    private static FragmentManager fm;
    private MailFolderInfoFragment defaultFoldersFragment, customFoldersFragment;
    private List<IMailFolderInfo> defaultMailFolderInfoList = new ArrayList<>();
    private List<IMailFolderInfo> customMailFolderInfoList = new ArrayList<>();
    private static String selectedFolderId;


    /**
     * Se ejecuta cuando se inicia la activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_menu);

        presenter = new InboxMenuPresenter(this, getApplicationContext());

        selectedFolderId = presenter.getSelectedFolderId();
        fm = getSupportFragmentManager();
        if (!defaultMailFolderInfoList.isEmpty()) {
            defaultMailFolderInfoList.clear();
        }
        if (!customMailFolderInfoList.isEmpty()) {
            customMailFolderInfoList.clear();
        }


        defaultMailFolderInfoList = presenter.getMailFolderInfos(false);
        customMailFolderInfoList = presenter.getMailFolderInfos(true);

        /**
         * Actualiza el frame en el que se muestran las carpetas creadas por defecto
         */
        updateFragment("defaultFoldersFragment");
        if (defaultMailFolderInfoList != null && fm.findFragmentByTag("defaultFoldersFragment") == null) {
            defaultFoldersFragment = new MailFolderInfoFragment(defaultMailFolderInfoList);
            setFragment(defaultFoldersFragment, R.id.frameDefaultFolders, "defaultFoldersFragment");
        }

        /**
         * Actualiza el frame en el que se muestran las carpetas creadas por el usuario
         */
        updateFragment("customFoldersFragment");
        if (customMailFolderInfoList != null && fm.findFragmentByTag("customFoldersFragment") == null) {
            customFoldersFragment = new MailFolderInfoFragment(customMailFolderInfoList);
            setFragment(customFoldersFragment, R.id.frameCreatedFolders, "customFoldersFragment");
        }


        updateTxtAccountInfo();

        /**
         * se cierra el menú (esta actividad) y vuelve a la bandeja de entrada
         */
        ImageButton btnExitInboxMenu = findViewById(R.id.btnExitInboxMenu);
        btnExitInboxMenu.setOnClickListener(v -> {
            startActivity(new Intent(this, InboxActivity.class));
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        });


        ImageButton btnExitToHome = findViewById(R.id.btnExitToHome);
        /**
         * se cierra sesión y vuelve a la pantalla inicial
         */
        btnExitToHome.setOnClickListener(v -> {
            presenter.signOut();
            startActivity(new Intent(this, HomeActivity.class));
        });

        ImageButton btnAccountOptions = findViewById(R.id.btnAccountOptions);
        ListView accountOptionsListView = findViewById(R.id.listAccountOptions);
        LinearLayout layoutInboxFolders = findViewById(R.id.layoutInboxFolders);

        Button btnAddFolder = findViewById(R.id.btnAddFolder);
        btnAddFolder.setOnClickListener(v -> {
            showDialog();
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        super.onResume();
        updateFragment("defaultFoldersFragment");
        updateFragment("customFoldersFragment");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, InboxActivity.class));
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    @Override
    public void setPresenter(IInboxMenuVP.Presenter presenter) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public static IInboxMenuVP.Presenter getPresenter() {
        return presenter;
    }

    /**
     *
     * @param fragment el fragment que se quiere inflar
     * @param idContainer identificador del layout donde se quiere inflar
     * @param tag etiqueta de nombre que le quieres poner
     */
    @Override
    public void setFragment(BaseFragment fragment, int idContainer, String tag) {
        //ataching to fragment the navigation presenter
        fragment.attachPresenter((FragmentNavigation.Presenter) presenter);
        //showing the presenter on screen
        getSupportFragmentManager()
                .beginTransaction()
                .replace(idContainer, fragment, tag)
                .commit();

    }

    /**
     * Actualiza el nombre y el email de la cuenta
     */
    @Override
    public void updateTxtAccountInfo() {
        TextView accountName = findViewById(R.id.txtAccountName);
        TextView accountEmail = findViewById(R.id.txtAccountEmail);
        presenter.setMailAccountInfo(accountName, accountEmail);
    }

    /**
     * actualiza el fragmento
     * @param tag
     */
    @Override
    public void updateFragment(String tag) {
        if (fm.findFragmentByTag(tag) != null) {
            fm.beginTransaction()
                    .detach(fm.findFragmentByTag(tag))
                    .attach(fm.findFragmentByTag(tag))
                    .commit();
        }

    }

    /**
     * Diálogo que se muestra para crear una nueva carpeta con el nombre que escribas.
     */
    public void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_add_folder_state, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        ImageButton btnSetFolderStateName = view.findViewById(R.id.btnSetFolderStateName);
        EditText txtSetFolderStateName = view.findViewById(R.id.txtSetFolderStateName);
        btnSetFolderStateName.setOnClickListener(v -> {
                    String text = txtSetFolderStateName.getText().toString();
                    presenter.createAccountFolder(text);
                    dialog.dismiss();
                }


        );


        dialog.show();
    }

    public static String getSelectedFolderId() {
        return selectedFolderId;
    }
}