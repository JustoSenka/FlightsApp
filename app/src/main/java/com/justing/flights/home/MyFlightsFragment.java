package com.justing.flights.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.internal.util.Predicate;
import com.justing.flights.R;
import com.justing.flights.adapters.FlightsArrayAdapter;
import com.justing.flights.core.FlightInfoFragment;
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
        final ListView lv = (ListView) view.findViewById(R.id.home_flights_list);

        Flight[] flights = new Flight[0];
        flights = AppData.getInstance().getCurrentUser().getMyFlights().toArray(flights);

        setInfoLabelText(view, flights);

        final FlightsArrayAdapter adapter = new FlightsArrayAdapter(context, android.R.layout.simple_list_item_1, flights);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FlightInfoFragment fragment = new FlightInfoFragment();

                long flightId = ((Flight)lv.getAdapter().getItem(position)).getId();
                Bundle bundle = new Bundle();
                bundle.putLong(FlightInfoFragment.KEY_FLIGHT_ID, flightId);
                fragment.setArguments(bundle);

                fragment.show(getFragmentManager(), "myflightInfoFragment");
            }
        });

        return view;
    }

    private void setInfoLabelText(View view, Flight[] flights) {
        TextView tv = (TextView) view.findViewById(R.id.myflight_info);

        if (flights.length == 0){
            tv.setHeight(100);
            tv.setText(getString(R.string.info_my_flights_empty));
        }
        else {
            tv.setText("");
            tv.setHeight(1);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
