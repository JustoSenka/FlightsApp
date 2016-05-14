package com.justing.flights.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.internal.util.Predicate;
import com.justing.flights.R;
import com.justing.flights.adapters.FlightsArrayAdapter;
import com.justing.flights.objects.AppData;
import com.justing.flights.objects.Flight;

public class MyFlightsFragment extends Fragment {

    private Context context;
    public MyFlightsFragment() {
    }

    public static MyFlightsFragment newInstance() {
        MyFlightsFragment fragment = new MyFlightsFragment();
        //Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_flights, container, false);
        ListView lv = (ListView) view.findViewById(R.id.home_flights_list);

        Flight[] flights = new Flight[0];
        flights = AppData.getInstance().getAvailableFlights().toArray(flights);

        final FlightsArrayAdapter adapter = new FlightsArrayAdapter(context, android.R.layout.simple_list_item_1, flights);
        lv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
