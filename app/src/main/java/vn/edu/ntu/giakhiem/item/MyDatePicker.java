package vn.edu.ntu.giakhiem.item;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MyDatePicker {
    ArrayList<IDatePicker> datePickerArrayList;
    Calendar calendar;
    Context context;
    DatePickerDialog dialog;
    DatePickerDialog.OnDateSetListener date;
    int index;

    public MyDatePicker() {
        datePickerArrayList = new ArrayList<>();
    }

    public MyDatePicker(Context context, Calendar calendar){
        this();
        this.calendar = calendar;
        this.context = context;
        makeDatePicker();
    }

    public void attach(IDatePicker datePicker){
        this.datePickerArrayList.add(datePicker);
    }

    public void detach(IDatePicker datePicker){
        this.datePickerArrayList.remove(datePicker);
    }

    private void makeDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                for (int i = 0; i < datePickerArrayList.size(); i++) {
                    datePickerArrayList.get(i).onClickGetDate(year, month, day, index);
                    dialog.updateDate(year, month, day);
                }
            }
        };

        dialog = new DatePickerDialog(context, date, year, month, day);
    }

    public void showPicker(int index){
        this.index = index;
        dialog.show();
    }
}
