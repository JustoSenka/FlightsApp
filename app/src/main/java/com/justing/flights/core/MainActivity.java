package com.justing.flights.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.justing.flights.home.HomeActivity;
import com.justing.flights.R;
import com.justing.flights.objects.AppData;
import com.justing.flights.objects.User;

public class MainActivity extends AppCompatActivity {

    private Button login, register, viewOffers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.main_log_in_button);
        register = (Button) findViewById(R.id.main_register_button);
        viewOffers = (Button) findViewById(R.id.main_view_offers_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
            }
        });

        viewOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.getInstance().setCurrentUser(new User());
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
            }
        });
    }
}
