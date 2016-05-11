package com.justing.flights.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.justing.flights.R;
import com.justing.flights.commons.StringTuple;
import com.justing.flights.objects.Flight;
import com.justing.flights.utils.DateFormatter;

public class FlightsArrayAdapter extends ArrayAdapter<Flight> {

    private final Context context;

    public FlightsArrayAdapter(Context context, int layout, Flight[] values) {
        super(context, layout, values);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_flight, parent, false);

        Flight e = getItem(position);

        ((TextView) rowView.findViewById(R.id.flight_departure)).setText(DateFormatter.getMonth(e.getDepartureTime()));
        ((TextView) rowView.findViewById(R.id.flight_arrival)).setText(DateFormatter.getMonth(e.getArrivalTime()));
        ((TextView) rowView.findViewById(R.id.flight_duration)).setText(DateFormatter.getTime(e.getDuration()));
        ((TextView) rowView.findViewById(R.id.flight_company)).setText(e.getCompany().name);
        ((TextView) rowView.findViewById(R.id.flight_from)).setText(e.getCityFrom());
        ((TextView) rowView.findViewById(R.id.flight_to)).setText(e.getCityTo() + " " + position);

        return rowView;
    }
}