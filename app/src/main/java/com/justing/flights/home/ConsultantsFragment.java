package com.justing.flights.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.justing.flights.R;
import com.justing.flights.adapters.TupleArrayAdapter;
import com.justing.flights.commons.StringTuple;

public class ConsultantsFragment extends Fragment {

    private Context context;
    private final StringTuple[] consultants = new StringTuple[]{
            new StringTuple("Wizz Air","+370 625 84476"),
            new StringTuple("Ryanair","+370 677 14582"),
            new StringTuple("Norwegian","+370 684 24561"),
            new StringTuple("AirBaltic","+370 689 92931")
    };

    public ConsultantsFragment() {}
    public static ConsultantsFragment newInstance() {
        return new ConsultantsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consultants, container, false);
        ListView lv = (ListView) view.findViewById(R.id.home_consultants_list);

        final TupleArrayAdapter adapter = new TupleArrayAdapter(context, android.R.layout.simple_list_item_1, consultants);
        lv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
