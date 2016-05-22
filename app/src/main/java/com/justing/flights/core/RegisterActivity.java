package com.justing.flights.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.justing.flights.home.HomeActivity;
import com.justing.flights.R;
import com.justing.flights.objects.AppData;
import com.justing.flights.objects.User;
import com.justing.flights.utils.DatabaseHandler;
import com.justing.flights.utils.DateFormatter;

import java.text.ParseException;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    private Button register;
    private EditText emailEdit, firstNameEdit, lastNameEdit, dateEdit, passEdit, passRepeatEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEdit = (EditText) findViewById(R.id.register_email);
        firstNameEdit = (EditText) findViewById(R.id.register_first_name);
        lastNameEdit = (EditText) findViewById(R.id.register_last_name);
        dateEdit = (EditText) findViewById(R.id.register_date);
        passEdit = (EditText) findViewById(R.id.register_password);
        passRepeatEdit = (EditText) findViewById(R.id.register_password2);

        register = (Button)findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDataCorrect()){
                    Intent i = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(i);

                    overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_slide_back_in, R.anim.activity_slide_back_out);
    }

    private boolean isDataCorrect() {
        EditText[] editsArr = new EditText[]{emailEdit, firstNameEdit, lastNameEdit, dateEdit, passEdit, passRepeatEdit};

        // Reset errors.
        for (EditText e : editsArr) {
            e.setError(null);
        }

        // Check for a valid input
        for (EditText e : editsArr) {
            if (TextUtils.isEmpty(e.getText().toString())) {
                e.setError(getString(R.string.error_field_required));
                e.requestFocus();
                return false;
            }
        }

        // Store values at the time of the login attempt.
        String email = emailEdit.getText().toString();
        String firstName = firstNameEdit.getText().toString();
        String lastName = lastNameEdit.getText().toString();
        String pass = passEdit.getText().toString();
        String passRepeat = passRepeatEdit.getText().toString();

        // Check if passwords matches
        if (!pass.equals(passRepeat)){
            passEdit.setError(getString(R.string.error_pass_no_match));
            passEdit.requestFocus();
            return false;
        }

        // Check Date format
        Date date;
        try {
            date = DateFormatter.fromYear(dateEdit.getText().toString());
        } catch (ParseException e) {
            dateEdit.setError(getString(R.string.error_wrong_format));
            dateEdit.requestFocus();
            return false;
        }

        registerUser(email, firstName, lastName, date, pass);
        return true;
    }

    private void registerUser(String email, String firstName, String lastName, Date birthDate, String pass){
        DatabaseHandler db = new DatabaseHandler(this);
        User u = new User(email, pass, firstName, lastName, birthDate);
        AppData.getInstance().setCurrentUser(u);
        db.addUser(u);
        Toast.makeText(this, "Logged in as: " + u.getFirstName() + " " + u.getLastName(), Toast.LENGTH_SHORT).show();
    }
}
