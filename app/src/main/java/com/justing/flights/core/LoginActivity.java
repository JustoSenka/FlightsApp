package com.justing.flights.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.justing.flights.home.HomeActivity;
import com.justing.flights.R;
import com.justing.flights.objects.AppData;
import com.justing.flights.objects.User;
import com.justing.flights.social.FacebookController;
import com.justing.flights.social.GoogleController;
import com.justing.flights.utils.DatabaseHandler;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.login_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


        ((Button) findViewById(R.id.login_facebook)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FacebookController.getInstance().promptLogin(getBaseContext());
            }
        });

        ((Button) findViewById(R.id.login_google)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleController.getInstance().promptLogin(getBaseContext());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_slide_back_in, R.anim.activity_slide_back_out);
    }

    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else if (checkCredentials(email, password)) {
            startActivity(new Intent(this, HomeActivity.class));
            overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
        }
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean checkCredentials(String email, String pass){

        DatabaseHandler db = new DatabaseHandler(this);
        User u = db.getUser(email);

        if (u == null){
            mEmailView.setError(getString(R.string.error_no_such_email));
            mEmailView.requestFocus();
        }
        else if (!u.getPassword().equals(pass)){
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            mPasswordView.requestFocus();
        }
        else {
            AppData.getInstance().setCurrentUser(u);
            Toast.makeText(this, "Logged in as: " + u.getFirstName() + " " + u.getLastName(), Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}

