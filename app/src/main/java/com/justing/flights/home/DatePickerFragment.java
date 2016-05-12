package com.justing.flights.home;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Justas on 05/12/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DateSetListener mListener;
    private Context context;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        return new DatePickerDialog(context, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
        if (context instanceof DateSetListener) {
            mListener = (DateSetListener) context;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        if (mListener != null) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            mListener.onDateSet(getArguments(), c.getTime());
        }
    }

    public interface DateSetListener {
        void onDateSet(Bundle args, Date date);
    }
}