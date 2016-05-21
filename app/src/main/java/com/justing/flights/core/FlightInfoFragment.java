package com.justing.flights.core;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.justing.flights.R;
import com.justing.flights.adapters.TupleArrayAdapter;
import com.justing.flights.objects.AppData;
import com.justing.flights.objects.Flight;
import com.justing.flights.utils.DateFormatter;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Justas on 05/12/2016.
 */
public class FlightInfoFragment extends DialogFragment {

    public static String KEY_FLIGHT_ID = "KEY_FLIGHT_ID";
    public static String KEY_PURCHASE_ENABLED = "KEY_PURCHASE_ENABLED";

    private Context context;
    private FlightPurchaseListener mListener;

    public FlightInfoFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);
        getDialog().setTitle(getString(R.string.flight_info));

        final Bundle args = getArguments();

        Flight f = AppData.getInstance().getFlightById(args.getLong(KEY_FLIGHT_ID));

        view.findViewById(R.id.info_purchase_button).setEnabled(args.getBoolean(KEY_PURCHASE_ENABLED, false));

        ((TextView) view.findViewById(R.id.info_flight_arrival)).setText(getString(R.string.flight_arrival) + ": " + DateFormatter.getFull(f.getArrivalTime()));
        ((TextView) view.findViewById(R.id.info_flight_departure)).setText(getString(R.string.flight_departure) + ": " + DateFormatter.getFull(f.getDepartureTime()));
        ((TextView) view.findViewById(R.id.info_duration)).setText(getString(R.string.flight_duration) + ": " + f.getDuration().toString());
        ((TextView) view.findViewById(R.id.info_flight_from)).setText(getString(R.string.flight_from) + ": " + f.getCityFrom());
        ((TextView) view.findViewById(R.id.info_flight_to)).setText(getString(R.string.flight_to) + ": " + f.getCityTo());
        ((TextView) view.findViewById(R.id.info_price)).setText(getString(R.string.price, f.getTicketPrice()));

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
        if (context instanceof FlightPurchaseListener) {
            mListener = (FlightPurchaseListener) context;
        }
    }

    public interface FlightPurchaseListener {
        void onFlightPurchase(Bundle args, Date date);
    }
}