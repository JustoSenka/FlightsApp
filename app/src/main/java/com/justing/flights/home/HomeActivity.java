package com.justing.flights.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.justing.flights.R;
import com.justing.flights.objects.AppData;
import com.justing.flights.objects.Company;
import com.justing.flights.objects.Flight;
import com.justing.flights.utils.DateFormatter;

import java.util.Date;
import java.util.SortedSet;

public class HomeActivity extends AppCompatActivity implements DatePickerFragment.DateSetListener {

    private static String DATE_PICKER_KEY = "DATE_PICKER_KEY";

    private HomePagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private Button searchFrom, searchTill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new HomePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // UI references (do not work, since fragments are not up yet)
        searchFrom = (Button) findViewById(R.id.date_picker_from);
        searchTill = (Button) findViewById(R.id.date_picker_till);

        readAvailableFlights();
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt(DATE_PICKER_KEY, v.getId());
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(Bundle args, Date date) {
        searchFrom = (Button) findViewById(R.id.date_picker_from);
        searchTill = (Button) findViewById(R.id.date_picker_till);
        int id = args.getInt(DATE_PICKER_KEY);

        if (id == searchFrom.getId()){
            searchFrom.setText(Html.fromHtml("<b><big>" + DateFormatter.getYear(date) + "</big></b>" +  "<br />" +
                    "<small>" + getString(R.string.search_min) + "</small>" + "<br />"));
        }
        else if (id == searchTill.getId()){
            searchTill.setText(Html.fromHtml("<b><big>" + DateFormatter.getYear(date) + "</big></b>" +  "<br />" +
                    "<small>" + getString(R.string.search_max) + "</small>" + "<br />"));
        }
    }










    void readAvailableFlights(){

        // TODO: Test data here now, replace with db (or don't?)

        final Date[] testDate = new Date[]{
                new Date(),
                new Date(new Date().getTime() + 7200000),
                new Date(new Date().getTime() + 15000000),
                new Date(new Date().getTime() + 88800000),
                new Date(new Date().getTime() + 92000000),
                new Date(new Date().getTime() + 100000000)
        };

        SortedSet<Flight> availableFlights = AppData.getInstance().getAvailableFlights();

        availableFlights.add(new Flight(testDate[0], testDate[1], "Vilnius", "London",Company.AirBaltic));
        availableFlights.add(new Flight(testDate[3], testDate[5], "London", "New York", Company.AirBaltic));
        availableFlights.add(new Flight(testDate[2], testDate[3], "Boston", "Warshawa", Company.Norwegian));
        availableFlights.add(new Flight(testDate[1], testDate[5], "Tampa", "Paris", Company.WizzAir));
        availableFlights.add(new Flight(testDate[2], testDate[5], "Boston", "Paris", Company.RyanAir));
        availableFlights.add(new Flight(testDate[3], testDate[5], "London", "Nimes", Company.WizzAir));
        availableFlights.add(new Flight(testDate[4], testDate[5], "Vilnius", "Paris", Company.RyanAir));
        availableFlights.add(new Flight(testDate[2], testDate[3], "Paris", "Nimes", Company.WizzAir));
        availableFlights.add(new Flight(testDate[0], testDate[1], "Vilnius", "London", Company.AirBaltic));
        availableFlights.add(new Flight(testDate[3], testDate[5], "London", "New York", Company.AirBaltic));
        availableFlights.add(new Flight(testDate[2], testDate[3], "Boston", "Warshawa", Company.Norwegian));
        availableFlights.add(new Flight(testDate[1], testDate[5], "Tampa", "Paris", Company.WizzAir));
    }
}
