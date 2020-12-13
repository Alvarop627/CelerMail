package com.celerapps.celermail.inboxGroup.mailViewer.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.celerapps.celermail.BaseFragment;
import com.celerapps.celermail.R;
import com.celerapps.celermail.inboxGroup.mailViewer.interfaces.IMailVP;
import com.celerapps.celermail.inboxGroup.mailViewer.presenter.MailPresenter;
import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.models.Mail;
import com.celerapps.celermail.shared.views.mailHistoryRow.MailHistoryFragment;

import java.util.ArrayList;
import java.util.List;

public class MailActivity extends AppCompatActivity implements IMailVP.View {

    private IMailVP.Presenter presenter;
    private static View scrollMailAtt;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mail);

        presenter = new MailPresenter(this, this);

        String mailId = getIntent().getStringExtra("mailId");
        IMail mail = presenter.getMail(mailId);
        IMail mailHistory1 = new Mail();
        mailHistory1.setBodyText("Sed viverra felis lacinia sapien porta mollis. Nulla imperdiet fringilla nisl. Ut sit amet diam dignissim, pretium dui eu, ultrices metus. Aenean ac lobortis odio. Phasellus consectetur augue id nisi viverra vulputate. Quisque vel tincidunt massa. Proin ultricies euismod condimentum. Quisque vel arcu lacinia, efficitur nulla sit amet, pharetra sapien. Nunc placerat fermentum velit at molestie.");
        mailHistory1.setSubject(mail.getSubject());
        mailHistory1.setSenderName(mail.getSenderName());

        IMail mailHistory2 = new Mail();
        mailHistory2.setBodyText("\n" +
                "Praesent ex massa, venenatis at purus et, pellentesque molestie erat. Morbi auctor dui lacus. Cras at gravida quam. Donec vel leo laoreet, ornare purus vel, fermentum lectus. Etiam cursus diam eu nisi dignissim imperdiet. Cras mattis interdum maximus. Cras pellentesque laoreet commodo. Proin a tortor ac orci pulvinar viverra et in erat. In dignissim posuere neque et viverra.");
        mailHistory2.setSubject(mail.getSubject());
        mailHistory2.setSenderName(mail.getSenderName());

        List<IMail> mailHistoryList = new ArrayList<IMail>();
        mailHistoryList.add(mailHistory1);
        mailHistoryList.add(mailHistory2);

        TextView txtMailSender = findViewById(R.id.txtMailSender);
        txtMailSender.setText(mail.getSenderName());

        TextView txtMailSubject = findViewById(R.id.txtMailSubject);
        txtMailSubject.setText(mail.getSubject());

        TextView txtPublicMsg = findViewById(R.id.txtMailMsg);
        txtPublicMsg.setText(mail.getBodyText());

        ImageView flagImportantMailMsg = findViewById(R.id.flagImportantMailMsg);

        if(mail.isImportant()){
            flagImportantMailMsg.setVisibility(View.VISIBLE);
        }else{
            flagImportantMailMsg.setVisibility(View.GONE);
        }

        Toolbar toolbar = findViewById(R.id.toolbarMail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FrameLayout frameMailHistory = findViewById(R.id.frameMailHistory);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameMailHistory, new MailHistoryFragment(mailHistoryList), "fragmentMailHistory");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        Button btnShowMailHistory = findViewById(R.id.btnShowMailHistory);
        btnShowMailHistory.setOnClickListener(v ->{
            if(frameMailHistory.getVisibility()== View.GONE){
                frameMailHistory.setVisibility(View.VISIBLE);
            }else{
                frameMailHistory.setVisibility(View.GONE);
            }
        });

        LinearLayout layoutTitleAttachments = findViewById(R.id.layoutTitleAttachments);
        ListView listAttachments = findViewById(R.id.listViewReceivedMailAttach);
        ImageView imgArrowMailAtt = findViewById(R.id.imgArrowMailAtt);

        layoutTitleAttachments.setOnClickListener(v -> {
            if(listAttachments.getVisibility()== View.GONE){
                listAttachments.setVisibility(View.VISIBLE);
                imgArrowMailAtt.setImageResource(R.drawable.arrow_up);

            }else{
                listAttachments.setVisibility(View.GONE);
                imgArrowMailAtt.setImageResource(R.drawable.arrow_down);

            }
        });

        final ListView listView = findViewById(R.id.listViewReceivedMailAttach);
        List<String> list = new ArrayList<>();
        addList(listView, list);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void addList(final ListView listView, List list){
        list.add("Archivo 1");
        list.add("Archivo 2");
        list.add("Archivo 3");
        list.add("Archivo 4");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String clickedItem=(String) listView.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),clickedItem,Toast.LENGTH_SHORT).show();
        });
    }

    public static void showScrollMailAtt(){
        scrollMailAtt.setVisibility(View.VISIBLE);
    }
    public static void hideScrollMailAtt(){
        scrollMailAtt.setVisibility(View.GONE);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void setPresenter(IMailVP.Presenter presenter) {

    }

    @Override
    public void setFragment(BaseFragment fragment, int idContainer) {

    }
}
