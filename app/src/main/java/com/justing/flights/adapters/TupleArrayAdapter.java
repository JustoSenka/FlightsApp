package com.justing.flights.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.justing.flights.R;
import com.justing.flights.commons.StringTuple;
import com.justing.flights.commons.Tuple;

public class TupleArrayAdapter extends ArrayAdapter<StringTuple> {

    private final Context context;

    public TupleArrayAdapter(Context context, int layout, StringTuple[] values) {
        super(context, layout, values);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_two_columns, parent, false);

        StringTuple e = getItem(position);

        TextView left = (TextView) rowView.findViewById(R.id.list_left);
        TextView right = (TextView) rowView.findViewById(R.id.list_right);

        left.setText(e.left);
        right.setText(e.right);

        return rowView;
    }
}