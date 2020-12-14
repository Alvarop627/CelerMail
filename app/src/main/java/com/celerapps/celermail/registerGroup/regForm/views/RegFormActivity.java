package com.celerapps.celermail.registerGroup.regForm.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.celerapps.celermail.R;
import com.celerapps.celermail.home.views.HomeActivity;
import com.celerapps.celermail.registerGroup.privacy.views.PrivacyActivity;
import com.celerapps.celermail.registerGroup.regForm.interfaces.IRegFormVP;
import com.celerapps.celermail.registerGroup.regForm.presenter.RegFormPresenter;
import com.celerapps.celermail.shared.interfaces.IMailAccount;
import com.celerapps.celermail.shared.models.MailAccount;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Esta clase define el formulario de registro de la aplicación.
 *
 * @author: Álvaro Reina Carrizosa
 */

public class RegFormActivity extends AppCompatActivity implements IRegFormVP.View {

    private Spinner spGender;
    private Spinner spCountrie;
    private IRegFormVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_form);

        spGender = findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this, R.array.gender,R.layout.support_simple_spinner_dropdown_item);
        spGender.setAdapter(adapterGender);

        spCountrie = findViewById(R.id.spCountrie);
        ArrayAdapter<CharSequence> adapterNat2 = ArrayAdapter.createFromResource(this, R.array.countries,R.layout.support_simple_spinner_dropdown_item);
        spCountrie.setAdapter(adapterNat2);

        Toolbar toolbar = findViewById(R.id.toolbarRegFormActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.colorPrimary,this.getTheme()));

        presenter = new RegFormPresenter(this);

        Button btnRegister = findViewById(R.id.btnRegister);
        EditText txtName = findViewById(R.id.editTextName);
        EditText txtSurname = findViewById(R.id.editTextSurname);
        EditText txtEmail = findViewById(R.id.editTextRegEmail);
        EditText txtPassword = findViewById(R.id.editTextRegPassword);
        EditText txtBirthdate = findViewById(R.id.editTextBirthdate);


        txtEmail.setText("@cmail.com");
        txtEmail.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        Selection.setSelection(txtEmail.getText(), txtEmail.getText().length());


        txtEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().endsWith("@cmail.com")) {
                    txtEmail.setText("@cmail.com");
                    Selection.setSelection(txtEmail.getText(), txtEmail.getText().length());

                }

            }
        });

        EditText txtPhone = findViewById(R.id.editTextPhone);
        Bitmap profilePhoto = BitmapFactory.decodeResource(getResources(),
                R.drawable.user_circle);
        btnRegister.setOnClickListener(v -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            Date date = new Date();
            try {
                date = sdf.parse(txtBirthdate.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long birthdateInMs = date.getTime();
            IMailAccount mAccount = new MailAccount(null,txtName.getText()+" "+txtSurname.getText(),txtEmail.getText().toString(),txtPassword.getText().toString(),spGender.getSelectedItem().toString(),spCountrie.getSelectedItem().toString(),profilePhoto,null,null,"0",birthdateInMs,txtPhone.getText().toString());
            presenter.createAccount(mAccount);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        Button btnCalendar = findViewById(R.id.btnCalendar);
        EditText editTextBornDate = findViewById(R.id.editTextBirthdate);
        btnCalendar.setOnClickListener(v -> {

            DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month+=1;
                    String fd = String.valueOf(dayOfMonth);
                    String fm = String.valueOf(month);
                    if(dayOfMonth<10) {fd = "0"+dayOfMonth;}
                    if(month<10) {fm = "0"+month;}
                    editTextBornDate.setText(fd + "/" + fm + "/" + year);
                }
            };

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(this,android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,mDateSetListener,year,month,day);
                dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "Aceptar",dialog);
                dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancelar",dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


        });
    }
    public void goToPrivacy(View view){
        Intent intent = new Intent(this, PrivacyActivity.class);
        startActivity(intent);
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
    public void setPresenter(IRegFormVP.Presenter presenter) {

    }


}
