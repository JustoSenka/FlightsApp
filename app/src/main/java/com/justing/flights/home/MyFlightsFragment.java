package com.justing.flights.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.justing.flights.R;
import com.justing.flights.adapters.FlightsArrayAdapter;
import com.justing.flights.adapters.TupleArrayAdapter;
import com.justing.flights.commons.StringTuple;
import com.justing.flights.objects.Company;
import com.justing.flights.objects.Flight;

import java.util.Date;

public class MyFlightsFragment extends Fragment {

    private Context context;

    private final Date[] testDate = new Date[]{
            new Date(),
            new Date(new Date().getTime() + 7200000),
            new Date(new Date().getTime() + 15000000),
            new Date(new Date().getTime() + 88800000),
            new Date(new Date().getTime() + 92000000),
            new Date(new Date().getTime() + 100000000)
    };

    private final Flight[] flights = new Flight[]{
            new Flight(testDate[0], testDate[1], "Vilnius", "London", Company.AirBaltic),
            new Flight(testDate[3], testDate[5], "London", "New York", Company.AirBaltic),
            new Flight(testDate[2], testDate[3], "Boston", "Warshawa", Company.Norwegian),
            new Flight(testDate[1], testDate[5], "Tampa", "Paris", Company.WizzAir),
            new Flight(testDate[2], testDate[5], "Boston", "Paris", Company.RyanAir),
            new Flight(testDate[3], testDate[5], "London", "Nimes", Company.WizzAir),
            new Flight(testDate[4], testDate[5], "Vilnius", "Paris", Company.RyanAir),
            new Flight(testDate[2], testDate[3], "Paris", "Nimes", Company.WizzAir),
            new Flight(testDate[0], testDate[1], "Vilnius", "London", Company.AirBaltic),
            new Flight(testDate[3], testDate[5], "London", "New York", Company.AirBaltic),
            new Flight(testDate[2], testDate[3], "Boston", "Warshawa", Company.Norwegian),
            new Flight(testDate[1], testDate[5], "Tampa", "Paris", Company.WizzAir)
    };
/*
    private final Flight[] flights = new Flight[]{
            new Flight(null, null, null, null, null),
            new Flight(null, null, null, null, null),
            new Flight(null, null, null, null, null)
    };

*/
    private Listener mListener;

    public MyFlightsFragment() {}
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

        final FlightsArrayAdapter adapter = new FlightsArrayAdapter(context, android.R.layout.simple_list_item_1, flights);
        lv.setAdapter(adapter);
/*
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height -= 1;
        lv.setLayoutParams(params);
        lv.requestLayout();
*/
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(0, null);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
/*
        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Listener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface Listener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int command, Bundle args);
    }
}
