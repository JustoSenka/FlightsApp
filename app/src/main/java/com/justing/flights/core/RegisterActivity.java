package com.justing.flights.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.justing.flights.home.HomeActivity;
import com.justing.flights.R;

public class RegisterActivity extends AppCompatActivity {

    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = (Button)findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDataCorrect()){
                    registerUser();
                    startActivity(new Intent(getBaseContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_slide_back_in, R.anim.activity_slide_back_out);
    }

    private boolean isDataCorrect(){
        // TODO: check data
        return  true;
    }

    private void registerUser(){
        // TODO: write to db user credentials
    }
}
