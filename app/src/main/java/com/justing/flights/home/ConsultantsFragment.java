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
import com.justing.flights.adapters.TwoColumnsAdapter;
import com.justing.flights.commons.StringTuple;
import com.justing.flights.objects.Flight;


public class ConsultantsFragment extends Fragment {

    private Context context;
    public StringTuple[] consultants = new StringTuple[]{
            new StringTuple("Wizz Air","+370 625 84476"),
            new StringTuple("Ryanair","+370 677 14582"),
            new StringTuple("Norwegian","+370 684 24561"),
            new StringTuple("AirBaltic","+370 689 92931")
    };

    private Listener mListener;

    public ConsultantsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultantsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultantsFragment newInstance(String param1, String param2) {
        ConsultantsFragment fragment = new ConsultantsFragment();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           // mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consultants, container, false);
        ListView lv = (ListView) view.findViewById(R.id.home_list);

        final TwoColumnsAdapter adapter = new TwoColumnsAdapter(context, android.R.layout.simple_list_item_1, consultants);
        lv.setAdapter(adapter);

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

        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Listener");
        }
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
