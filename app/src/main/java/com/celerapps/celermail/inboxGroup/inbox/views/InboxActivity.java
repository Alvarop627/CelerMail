package com.celerapps.celermail.inboxGroup.inbox.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.FragmentNavigation;
import com.celerapps.celermail.R;
import com.celerapps.celermail.inboxGroup.inbox.interfaces.IInboxVP;
import com.celerapps.celermail.inboxGroup.inbox.presenters.InboxPresenter;
import com.celerapps.celermail.inboxGroup.inbox.views.mailRow.MailContainerFragment;
import com.celerapps.celermail.inboxGroup.inboxMenu.views.InboxMenuActivity;
import com.celerapps.celermail.inboxGroup.search.views.SearchActivity;
import com.celerapps.celermail.inboxGroup.mailComposer.views.MailComposerActivity;
import com.celerapps.celermail.shared.interfaces.IMailContainer;
import com.celerapps.celermail.shared.models.FragmentDescriptor;
import com.celerapps.celermail.shared.models.MailContainer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

/**
 * Esta clase define la bandeja de entrada.
 *
 * @author: Álvaro Reina Carrizosa
 */
public class InboxActivity extends AppCompatActivity implements IInboxVP.View {

    public IInboxVP.Presenter presenter;
    private FragmentDescriptor[] fragmentsDescriptorCollection;
    private static View buttonsInbox;
    private String folderName;

    /**
     * Se ejecuta cuando se inicia la activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);


        /**
         * folderId es el identificador de la carpeta seleccionada
         */
        String folderId = getIntent().getStringExtra("folderId");
        boolean isUserCreated = getIntent().getBooleanExtra("isUserCreated",false);
         if(getIntent().getStringExtra("folderName")!=null){
             folderName = getIntent().getStringExtra("folderName");
         }else{
             folderName = getResources().getString(R.string.inbox);
         }

        if(folderId==null){
            folderId="0";
        }



        presenter = new InboxPresenter(this,getApplicationContext());

        TextView textToolbar = findViewById(R.id.txtToolbarInbox);

        textToolbar.setText(folderName);

        /**
         * a continuación se crean los fragmentos que se van a inflar y se asocian con el viewpager2 y los tabs
         */
        fragmentsDescriptorCollection = new FragmentDescriptor[2];
        List<IMailContainer> mailContainers0 = presenter.getMailContainers(folderId,isUserCreated,false);
        List<IMailContainer> mailContainers1 = presenter.getMailContainers(folderId,isUserCreated,true);
        if(mailContainers0!=null) {
            fragmentsDescriptorCollection[0] = new FragmentDescriptor(new MailContainerFragment(mailContainers0), "Bandeja");
        }
        if(mailContainers1!=null) {
            fragmentsDescriptorCollection[1] = new FragmentDescriptor(new MailContainerFragment(mailContainers1), "Importantes");
        }
        SectionsPagerAdapterMail sectionsPagerAdapterMail = new SectionsPagerAdapterMail(this, fragmentsDescriptorCollection);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(sectionsPagerAdapterMail);

        TabLayout tabInbox = findViewById(R.id.tabInbox);

        buttonsInbox = findViewById(R.id.buttonsLayout);

        new TabLayoutMediator(tabInbox, viewPager,
                (tab, position) -> tab.setText(fragmentsDescriptorCollection[position].getTitle())
        ).attach();


        tabInbox.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary));

        FloatingActionButton fabSearch = findViewById(R.id.fabSearch);

        /**
         * te lleva a la pantalla de búsqueda
         */
        fabSearch.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });

        FloatingActionButton fabNewMail = findViewById(R.id.fabNewMail);

        /**
         * Te dirige a redactar un nuevo correo
         */
        fabNewMail.setOnClickListener(v -> {
            Intent intent = new Intent(this, MailComposerActivity.class);
            startActivity(intent);
        });

        ImageButton btnInboxMenu = findViewById(R.id.btnInboxMenu);
        btnInboxMenu.setOnClickListener(v -> {
            startActivity(new Intent(this, InboxMenuActivity.class));
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
        });

        Button btnA = findViewById(R.id.btnA);
        btnA.setOnClickListener(v -> {
            Toast t = Toast.makeText(v.getContext(), "Botón A pulsado", Toast.LENGTH_SHORT);
            t.show();
        });

        Button btnB = findViewById(R.id.btnB);
        btnB.setOnClickListener(v -> {
            Toast t = Toast.makeText(v.getContext(), "Botón B pulsado", Toast.LENGTH_SHORT);
            t.show();
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void setFragment(BaseFragment fragment, int idContainer) {
        //ataching to fragment the navigation presenter
        fragment.attachPresenter((FragmentNavigation.Presenter) presenter);
        //showing the presenter on screen
        getSupportFragmentManager()
                .beginTransaction()
                .replace(idContainer,fragment)
                .commit();
    }

    @Override
    public void setPresenter(IInboxVP.Presenter presenter) {

    }

    /**
     * pone visible la barra de botones. Se usa al seleccionar algún checkbox.
     */
    public static void showInboxButtons() {
        buttonsInbox.setVisibility(View.VISIBLE);
    }

    /**
     * pone invisible la barra de botones. Se usa al deseleccionar algún checkbox.
     */
    public static void hideInboxButtons() {
        buttonsInbox.setVisibility(View.GONE);
    }

    /**
     * Al dar click en el botón se despliega un menú que te permite modificar varias opciones del correo en concreto.
     * @param button
     */
    public static void showMailOptionsMenu(Button button) {
        PopupMenu popup = new PopupMenu(button.getContext(), button);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.mail_options_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                //Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        popup.setGravity(Gravity.END);
        popup.show();//showing popup menu
    }
}
