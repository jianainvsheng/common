package com.source.sdk.common.datapicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.DatePicker;

import java.util.Calendar;

public class SysDatePicker {

    public static Dialog showSysDate(Context context, final PickDateListener pickDateListener){
        final Calendar c = Calendar.getInstance();
        final DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                if(pickDateListener != null)
                    pickDateListener.onDateStr(DateFormat.format("yyy-MM-dd", c));

            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
        return dialog;
    }

    public interface PickDateListener{
        void onDateStr(CharSequence dateStr);
    }
}
