package com.justing.flights.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.TypedArrayUtils;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.justing.flights.R;
import com.justing.flights.adapters.FlightsArrayAdapter;
import com.justing.flights.adapters.TupleArrayAdapter;
import com.justing.flights.commons.StringTuple;
import com.justing.flights.objects.AppData;
import com.justing.flights.objects.Company;
import com.justing.flights.objects.Flight;
import com.justing.flights.utils.ArrayUtils;
import com.justing.flights.utils.DateFormatter;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class SearchFragment extends Fragment {

    private Context context;
    private Listener mListener;

    public SearchFragment() {}
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Spinner spinFrom = (Spinner) view.findViewById(R.id.city_from);
        Spinner spinTo = (Spinner) view.findViewById(R.id.city_to);

        ((Button) view.findViewById(R.id.date_picker_from)).setText(Html.fromHtml("<b><big>" + DateFormatter.getYear(new Date()) + "</big></b>" +  "<br />" +
                "<small>" + getString(R.string.search_min) + "</small>" + "<br />"));
        ((Button)view. findViewById(R.id.date_picker_till)).setText(Html.fromHtml("<b><big>" + DateFormatter.getYear(new Date()) + "</big></b>" +  "<br />" +
                "<small>" + getString(R.string.search_max) + "</small>" + "<br />"));

        String[] cities = ArrayUtils.concat(new String[]{getString(R.string.any)}, AppData.getInstance().getKnownCities());

        spinFrom.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, cities));
        spinTo.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, cities));

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
