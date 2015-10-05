package com.kylehebert.criminalintent;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Date;

/**
 * Created by kylehebert on 10/5/15. This Fragment will be hosted by
 * CrimePagerActivity and display an Alert Dialog containing a DatePicker
 */
public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE = "date";

    private DatePicker mDatePicker;

    /*
    a call to newInstance will stash the date selected in the DatePicker
    into the arguments bundle, from here the fragment can access it
     */
    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(args);
        return datePickerFragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

}
