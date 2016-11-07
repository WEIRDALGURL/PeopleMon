package com.meowisthetime.peoplemon.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.meowisthetime.peoplemon.Models.Account;
import com.meowisthetime.peoplemon.Network.RestClient;
import com.meowisthetime.peoplemon.PeopleMonApplication;
import com.meowisthetime.peoplemon.R;
import com.meowisthetime.peoplemon.Stages.LoginStage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.meowisthetime.peoplemon.Components.Constants.apiKey;
import static com.meowisthetime.peoplemon.Components.Constants.avatar;

/**
 * Created by sheamaynard on 10/31/16.
 */

public class RegisterView extends LinearLayout {
    private Context context;

    @Bind(R.id.username_field)
    EditText usernameField;

    @Bind(R.id.email_field)
    EditText emailField;

    @Bind(R.id.password_field)
    EditText passwordField;

    @Bind(R.id.confirm_field)
    EditText confirmField;

    @Bind(R.id.register_button)
    Button registerButton;

    @Bind(R.id.spinner)
    ProgressBar spinner;


    public RegisterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.register_button)
    public void register() {
        InputMethodManager imm = (InputMethodManager)context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(usernameField.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(emailField.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(passwordField.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(confirmField.getWindowToken(), 0);

        String username = usernameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String confirm = confirmField.getText().toString();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            Toast.makeText(context, R.string.fill_out_all_fields, Toast.LENGTH_LONG).show();

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, R.string.provide_valid_email, Toast.LENGTH_LONG).show();

        }else if (!password.equals(confirm)) {
            Toast.makeText(context, R.string.passwords_not_match, Toast.LENGTH_LONG).show();
            
        } else {
            registerButton.setEnabled(false);
            spinner.setVisibility(VISIBLE);

            Account account = new Account(email, username, avatar, apiKey, password);
            RestClient restClient = new RestClient();
            restClient.getApiService().register(account).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
//                        Account authUser = response.body();
//                        UserStore.getInstance().setToken(authUser.getToken());
//                        UserStore.getInstance().setTokenExpiration(authUser.getExpiration());

                        Flow flow = PeopleMonApplication.getMainFlow();
                        History newHistory = History.single(new LoginStage());
                        flow.setHistory(newHistory, Flow.Direction.REPLACE);

                    } else {
                        resetView();
                        Toast.makeText(context, R.string.registration_failed + ":" + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    resetView();
                    Toast.makeText(context, R.string.registration_failed, Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    private void resetView() {
        registerButton.setEnabled(true);
        spinner.setVisibility(GONE);

    }
}
