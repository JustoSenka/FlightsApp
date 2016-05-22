package com.justing.flights.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.justing.flights.R;
import com.justing.flights.core.FlightsActivity;
import com.justing.flights.objects.AppData;
import com.justing.flights.utils.ArrayUtils;
import com.justing.flights.utils.DateFormatter;

import java.util.Date;

public class SearchFragment extends Fragment {

    private Context context;
    private OnSearchButtonClick mListener;

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
        final Spinner spinFrom = (Spinner) view.findViewById(R.id.city_from);
        final Spinner spinTo = (Spinner) view.findViewById(R.id.city_to);
        final Button dateFrom = (Button) view.findViewById(R.id.date_picker_from);
        final Button dateTill = (Button)view. findViewById(R.id.date_picker_till);

        // Fill date picker buttons with current date and label
        dateFrom.setText(Html.fromHtml("<b><big>" + DateFormatter.getYear(new Date()) +
                "</big></b>" +  "<br />" + "<small>" + getString(R.string.search_min) + "</small>" + "<br />"));
        dateTill.setText(Html.fromHtml("<b><big>" + DateFormatter.getYear(new Date()) +
                "</big></b>" +  "<br />" + "<small>" + getString(R.string.search_max) + "</small>" + "<br />"));

        // Fill spinners with city names
        String[] cities = ArrayUtils.concat(new String[]{getString(R.string.any)}, AppData.getInstance().getKnownCities());
        spinFrom.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, cities));
        spinTo.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, cities));

        // Set search button listener, read UI, set up bundle args
        ((Button) view.findViewById(R.id.search_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(FlightsActivity.KEY_CITY_FROM, spinFrom.getSelectedItem().toString());
                    bundle.putString(FlightsActivity.KEY_CITY_TO, spinTo.getSelectedItem().toString());
                    bundle.putString(FlightsActivity.KEY_DATE_FROM, dateFrom.getText().toString().substring(0, 10));
                    bundle.putString(FlightsActivity.KEY_DATE_TILL, dateTill.getText().toString().substring(0, 10));

                    mListener.onSearchButtonClick(bundle);
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        if (context instanceof OnSearchButtonClick) {
            mListener = (OnSearchButtonClick) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSearchButtonClick {
        void onSearchButtonClick(Bundle args);
    }
}
