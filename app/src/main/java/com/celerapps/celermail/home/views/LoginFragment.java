package com.celerapps.celermail.home.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.celerapps.celermail.R;
import com.celerapps.celermail.home.interfaces.IHomeVP;
import com.celerapps.celermail.home.presenter.HomePresenter;
import com.celerapps.celermail.inboxGroup.inbox.views.InboxActivity;
import com.celerapps.celermail.registerGroup.terms.views.TermsActivity;

public class LoginFragment extends Fragment {

    private IHomeVP.Presenter presenter;
    private static FragmentActivity fragmentActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        presenter = new HomePresenter((IHomeVP.View) this.getActivity());
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        Button btnReg = view.findViewById(R.id.btnReg);

        btnReg.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TermsActivity.class);
            startActivity(intent);
        });

        EditText txtUser = view.findViewById(R.id.editTextUser);
        EditText txtPassword = view.findViewById(R.id.editTextPassword);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            presenter.signIn(txtUser.getText().toString(), txtPassword.getText().toString());
        });

        fragmentActivity = this.getActivity();
        return view;
    }

    public static FragmentActivity getFragmentActivity(){
        return fragmentActivity;
    }


}
