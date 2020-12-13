package com.celerapps.celermail.inboxGroup.mailComposer.views;

/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.celerapps.celermail.R;
import com.celerapps.celermail.inboxGroup.inbox.views.InboxActivity;
import com.celerapps.celermail.inboxGroup.attachments.views.AttachmentsActivity;
import com.celerapps.celermail.inboxGroup.mailComposer.interfaces.IMailComposerPresenter;
import com.celerapps.celermail.inboxGroup.mailComposer.interfaces.IMailComposerView;
import com.celerapps.celermail.inboxGroup.mailComposer.presenter.MailComposerPresenter;
import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.models.Mail;

import net.dankito.richtexteditor.android.RichTextEditor;
import net.dankito.richtexteditor.android.toolbar.GroupedCommandsEditorToolbar;
import net.dankito.richtexteditor.model.DownloadImageConfig;
import net.dankito.richtexteditor.model.DownloadImageUiSetting;
import net.dankito.utils.android.permissions.IPermissionsService;
import net.dankito.utils.android.permissions.PermissionsService;

import java.io.File;

public class MailComposerActivity extends AppCompatActivity implements IMailComposerView {

    private RichTextEditor editor;

    private GroupedCommandsEditorToolbar bottomGroupedCommandsToolbar;

    private IPermissionsService permissionsService = new PermissionsService(this);

    private IMailComposerPresenter presenter;
    private String bodyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_composer);
        Toolbar toolbar = findViewById(R.id.toolbarMailComposerActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        editor = findViewById(R.id.editor);

        // this is needed if you like to insert images so that the user gets asked for permission to access external storage if needed
        // see also onRequestPermissionsResult() below
        editor.setPermissionsService(permissionsService);
        bottomGroupedCommandsToolbar = findViewById(R.id.editorToolbarMsg);
        bottomGroupedCommandsToolbar.setEditor(editor);


        // you can adjust predefined toolbars by removing single commands
//        bottomGroupedCommandsToolbar.removeCommandFromGroupedCommandsView(CommandName.TOGGLE_GROUPED_TEXT_STYLES_COMMANDS_VIEW, CommandName.BOLD);
//        bottomGroupedCommandsToolbar.removeSearchView();

        editor.setEditorFontSize(20);
        editor.setPadding((4 * (int) getResources().getDisplayMetrics().density));

        // some properties you also can set on editor
//        editor.setEditorBackgroundColor(Color.YELLOW)
//        editor.setEditorFontColor(Color.MAGENTA)
//        editor.setEditorFontFamily("cursive")

        // show keyboard right at start up
        //editor.focusEditorAndShowKeyboardDelayed();

        // only needed if you allow to automatically download remote images
        editor.setDownloadImageConfig(new DownloadImageConfig(DownloadImageUiSetting.AllowSelectDownloadFolderInCode,
                new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "downloaded_images")));


        /*      Set listeners on RichTextEditor         */

        // get informed when edited HTML changed
        editor.addDidHtmlChangeListener(didHtmlChange -> {
            // e.g. set save button to enabled / disabled
            // btnSave.setEnabled(didHtmlChange);
        });

        // use this listener with care, it may decreases performance tremendously
        editor.addHtmlChangedListener(html -> {
            // htmlChangedAsync() gets called on a background thread, so if you want to use it on UI thread you have to call runOnUiThread()
        });

        EditText txtReceiver = findViewById(R.id.editTxtReceiver);
        EditText txtCC = findViewById(R.id.editTxtCC);
        EditText txtCCO = findViewById(R.id.editTxtCCO);
        EditText txtSubject = findViewById(R.id.editTxtSubject);
        Button btnNext = findViewById(R.id.btnMailComposerNext);
        String CC = txtCC.getText().toString();
        String CCO = txtCCO.getText().toString();
        if (CC.equals("")) {
            CC = null;
        }
        if (CCO.equals("")) {
            CCO = null;
        }
        String finalCC = CC;
        String finalCCO = CCO;

        btnNext.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Spanned text = Html.fromHtml(editor.getCachedHtml(), Html.FROM_HTML_MODE_COMPACT);
                bodyText = text.toString();
            } else {
                Spanned text = Html.fromHtml(editor.getCachedHtml());
                bodyText = text.toString();
            }
            Bundle b = new Bundle();
            IMail mMail = new Mail(null,null,null, txtReceiver.getText().toString(), finalCC, finalCCO, txtSubject.getText().toString(), bodyText, 0,null, null);
            b.putParcelable("mail", mMail);
            Intent intent = new Intent(this, AttachmentsActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        });

        ImageButton btnShowReceiverOptions = findViewById(R.id.btnShowReceiverOptions);
        EditText editTxtCC = findViewById(R.id.editTxtCC);
        EditText editTxtCCO = findViewById(R.id.editTxtCCO);
        btnShowReceiverOptions.setOnClickListener(v -> {
            if (editTxtCC.getVisibility() == View.GONE && editTxtCCO.getVisibility() == View.GONE) {
                editTxtCC.setVisibility(View.VISIBLE);
                editTxtCCO.setVisibility(View.VISIBLE);
            } else {
                editTxtCC.setVisibility(View.GONE);
                editTxtCCO.setVisibility(View.GONE);
            }
        });

        presenter = new MailComposerPresenter(this);
    }

    public void goToInbox(View view) {
        Intent intent = new Intent(this, InboxActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    // Important: Overwrite onBackPressed and pass it to toolbar.There's no other way that it can get informed of back button presses.
    @Override
    public void onBackPressed() {
        if (!bottomGroupedCommandsToolbar.handlesBackButtonPress()) {
            super.onBackPressed();
        }
    }

    // only needed if you like to insert images from local device so the user gets asked for permission to access external storage if needed

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsService.onRequestPermissionsResult(requestCode, permissions, grantResults);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    // then when you want to do something with edited html
    /*private void save() {
        editor.getCurrentHtmlAsync(html -> {
            saveHtml(html);
        });
    }

    private void saveHtml(String html) {
        // ...
    }*/

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void setPresenter(IMailComposerPresenter presenter) {

    }
}
