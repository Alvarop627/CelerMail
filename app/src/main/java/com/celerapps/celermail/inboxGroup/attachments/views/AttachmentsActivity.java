package com.celerapps.celermail.inboxGroup.attachments.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.celerapps.celermail.R;
import com.celerapps.celermail.inboxGroup.inbox.views.InboxActivity;
import com.celerapps.celermail.inboxGroup.attachments.interfaces.IAttachmentsPresenter;
import com.celerapps.celermail.inboxGroup.attachments.interfaces.IAttachmentsView;
import com.celerapps.celermail.inboxGroup.attachments.presenter.AttachmentsPresenter;
import com.celerapps.celermail.inboxGroup.mailComposer.views.MailComposerActivity;
import com.celerapps.celermail.shared.interfaces.IAttachment;
import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.models.Attachment;
import com.celerapps.celermail.shared.views.attachmentRow.AttachmentFragment;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Esta clase define la pantalla donde añaden los adjuntos a un mail y se envía.
 *
 * @author: Álvaro Reina Carrizosa
 */

public class AttachmentsActivity extends AppCompatActivity implements IAttachmentsView {

    private static ArrayList<IAttachment> listAttachments = new ArrayList<>();

    private ArrayAdapter<String> arrayAdapter;

    private int clickCounter = 0;

    private ListView listView;

    private IAttachmentsPresenter presenter;

    private AttachmentFragment fragment;

    private static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attachments);

        Toolbar toolbar = findViewById(R.id.toolbarAttachmentsActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        presenter = new AttachmentsPresenter(this);
        fm = getSupportFragmentManager();


        fragment = new AttachmentFragment(this.listAttachments);
        setFragment(fragment,R.id.frameAttachments);

        /**
         * Se obtiene un mail que viene incompleto todavía.
         * Más adelante se termina de rellenar con campos.
         */
        IMail mMail = getIntent().getExtras().getParcelable("mail");

        Button btnAddAttachment = (Button) findViewById(R.id.btnAddAttachments);

        btnAddAttachment.setOnClickListener(v -> {
            listAttachments.add(new Attachment());
            updateFragment();
        });

        CheckBox checkBoxIsImportant = findViewById(R.id.checkBoxIsImportant);

        Button btnNext = findViewById(R.id.btnAttachmentsToNext);
        /**
         * Se termina de componer el nuevo email, se crea y se envía.
         */
        btnNext.setOnClickListener(v -> {
            mMail.setAttachments(null);
            mMail.setSenderName(presenter.getSelectedAccountName());
            mMail.setSenderAddress(presenter.getSelectedAccountEmail());
            mMail.setDateTimeInMs(System.currentTimeMillis());
            mMail.setImportant(checkBoxIsImportant.isChecked());
            mMail.setFolderId("4");

            try{
                presenter.createMail(mMail);
                Intent intent = new Intent(this, InboxActivity.class);
                Toast t = Toast.makeText(this,"Enviando mensaje...",Toast.LENGTH_SHORT);
                t.show();
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
                Log.e("","Error creating email");
            }


        });

    }

    public void goToPrevious(View view) {
        Intent intent = new Intent(this, MailComposerActivity.class);
        startActivity(intent);
    }

    public void goToInbox(View view) {
        Intent intent = new Intent(this, InboxActivity.class);
        Toast t = Toast.makeText(this, "Enviando mensaje...", Toast.LENGTH_SHORT);
        t.show();
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void setPresenter(IAttachmentsPresenter presenter) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public static void updateFragment() {
        fm.beginTransaction()
                .detach(fm.findFragmentByTag("attachmentsFragment"))
                .attach(fm.findFragmentByTag("attachmentsFragment"))
                .commit();
    }

    @Override
    public void setFragment(Fragment fragment, int idContainer) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(idContainer, fragment,"attachmentsFragment")
                .commit();
    }

    public static ArrayList<IAttachment> getListAttachments() {
        return listAttachments;
    }
}
