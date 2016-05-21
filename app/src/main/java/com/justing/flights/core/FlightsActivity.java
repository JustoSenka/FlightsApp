package com.justing.flights.core;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.util.Predicate;
import com.justing.flights.R;
import com.justing.flights.adapters.FlightsArrayAdapter;
import com.justing.flights.objects.AppData;
import com.justing.flights.objects.Flight;
import com.justing.flights.utils.DateFormatter;

import java.text.ParseException;
import java.util.Date;
import java.util.SortedSet;

public class FlightsActivity extends AppCompatActivity {

    public static final String KEY_DATE_FROM = "KEY_DATE_FROM";
    public static final String KEY_DATE_TILL = "KEY_DATE_TILL";
    public static final String KEY_CITY_FROM = "KEY_CITY_FROM";
    public static final String KEY_CITY_TO = "KEY_CITY_TO";

    private Date dateFrom, dateTill;
    private String cityTo, cityFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);
        readSearchArgs(getIntent().getExtras());

        final Flight[] flights = getFilteredDataWithArgs();
        setInfoLabelText(flights);

        final ListView lv = (ListView) findViewById(R.id.flights_list);
        lv.setAdapter(new FlightsArrayAdapter(this, android.R.layout.simple_list_item_1, flights));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FlightInfoFragment fragment = new FlightInfoFragment();

                final long flightId = ((Flight)lv.getAdapter().getItem(position)).getId();
                Bundle bundle = new Bundle();
                bundle.putLong(FlightInfoFragment.KEY_FLIGHT_ID, flightId);

                if (!isAlreadyBookedFlight(flightId)){
                    bundle.putBoolean(FlightInfoFragment.KEY_PURCHASE_ENABLED, true);
                }

                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "flightInfoFragment");
            }
        });
    }

    private void setInfoLabelText(Flight[] flights) {
        TextView tv = (TextView) findViewById(R.id.flights_info);

        if (flights.length == 0){
            tv.setHeight(100);
            tv.setText(getString(R.string.info_search_results_empty));
            }
        else {
            tv.setText("");
            tv.setHeight(1);
        }
    }

    private boolean isAlreadyBookedFlight(final long flightId){

        SortedSet<Flight> set = AppData.getInstance().getFilteredFlights(new Predicate<Flight>() {
            @Override
            public boolean apply(Flight flight) {
                return flightId == flight.getId();
            }
        });

        return set.size() >= 0;
    }

    private void readSearchArgs(Bundle args) {
        cityFrom = args.getString(KEY_CITY_FROM);
        cityTo = args.getString(KEY_CITY_TO);
        try {
            dateFrom = DateFormatter.fromYear(args.getString(KEY_DATE_FROM));
        } catch (ParseException e) {
            dateFrom = new Date();
            Toast.makeText(this, "Date from is not in correct format: " + args.getString(KEY_DATE_FROM), Toast.LENGTH_LONG).show();
        }
        try {
            dateTill = DateFormatter.fromYear(args.getString(KEY_DATE_TILL));
        } catch (ParseException e) {
            dateTill = new Date();
            Toast.makeText(this, "Date till is not in correct format: " + args.getString(KEY_DATE_TILL), Toast.LENGTH_LONG).show();
        }
    }

    private Flight[] getFilteredDataWithArgs() {
        Flight[] flights = new Flight[0];
        flights = AppData.getInstance().getFilteredFlights(new Predicate<Flight>() {
            @Override
            public boolean apply(Flight f) {
                if (!f.getCityFrom().equals(cityFrom) && !cityFrom.equals(getString(R.string.any))) return false;
                if (!f.getCityTo().equals(cityTo) && !cityTo.equals(getString(R.string.any))) return false;
                if (f.getDepartureTime().getTime() <= dateFrom.getTime() ||
                        f.getDepartureTime().getTime() > dateTill.getTime() + 86400000) return false;

                return true;
            }
        }).toArray(flights);

        return flights;
    }
}
