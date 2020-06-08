package vn.edu.ntu.giakhiem.item;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MyTimePicker {
    ArrayList<ITimePicker> timePickerArrayList;
    Calendar calendar;
    TimePickerDialog timePickerDialog;
    TimePickerDialog.OnTimeSetListener time;
    Context context;

    int index;

    public MyTimePicker() {
        timePickerArrayList = new ArrayList<>();
    }

    public MyTimePicker(Context context, Calendar calendar) {
        this();
        this.context = context;
        this.calendar = calendar;
        makeTimePicker();
    }

    public void attach(ITimePicker iTimePicker){
        timePickerArrayList.add(iTimePicker);
    }

    public void dettach(ITimePicker iTimePicker){
        timePickerArrayList.remove(iTimePicker);
    }


    public void makeTimePicker(){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                for (int i = 0; i < timePickerArrayList.size(); i++){
                    timePickerArrayList.get(i).onClickGetTime(hourOfDay, minute, index);
                    timePickerDialog.updateTime(hourOfDay,minute);
                }
            }
        };

        timePickerDialog = new TimePickerDialog(context, time, hour, minute, true);
    }

    public void showPicker(int index){
        this.index = index;
        timePickerDialog.show();
    }
}
